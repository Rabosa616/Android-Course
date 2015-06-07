package com.example.rabosa.pois;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener ,LocationListener {
    private LocationManager manejador;
    private Location mejorLocaliz;
    public BaseAdapter adaptador;
    private static final long DOS_MINUTOS = 2 * 60 * 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        Lugares.indicializaBD(this);
        //adaptador = new AdaptadorLugares(this);
        /*adaptador = new SimpleCursorAdapter(this,
                R.layout.elemento_lista,
                Lugares.listado(),
                new String[] { "nombre", "direccion"},
                new int[] { R.id.nombre, R.id.direccion}, 0);*/
        manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(manejador.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            actualizaMejorLocaliz(manejador.getLastKnownLocation(
                    LocationManager.GPS_PROVIDER));
        }
        if(manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            actualizaMejorLocaliz(manejador.getLastKnownLocation(
                    LocationManager.NETWORK_PROVIDER));
        }
        iniciarVistas();
    }

    private void iniciarVistas() {
        adaptador = new AdaptadorCursorLugares(this, Lugares.listado());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }
    private void actualizarVistas()
    {
        ListView listView = (ListView) findViewById(R.id.listView);
        AdaptadorCursorLugares adaptador =(AdaptadorCursorLugares)listView.getAdapter();
        adaptador.changeCursor(Lugares.listado());
    }

    @Override protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        activarProveedores();
    }

    @Override protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
        manejador.removeUpdates((LocationListener) this);
    }

    @Override protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView parent,View vista,
                               int posicion, long id){
        Intent i = new Intent(this, PoiEditorActivity.class);
        i.putExtra("id", id);
        startActivityForResult(i,0);
    }

    @Override public void onLocationChanged(Location location) {
        Log.d(Lugares.TAG, "Nueva localización: "+location);
        actualizaMejorLocaliz(location);
    }


    @Override public void onProviderDisabled(String proveedor) {
        Log.d(Lugares.TAG, "Se deshabilita: " + proveedor);
        activarProveedores();
    }

    @Override    public void onProviderEnabled(String proveedor) {
        Log.d(Lugares.TAG, "Se habilita: "+proveedor);
        activarProveedores();
    }

    @Override
    public void onStatusChanged(String proveedor, int estado, Bundle extras) {
        Log.d(Lugares.TAG, "Cambia estado: "+proveedor);
        activarProveedores();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_search) {
            lanzarVistaLugar(null);
            return true;
        }
        if (id==R.id.menu_mapa) {
            Intent i = new Intent(this, Mapa.class);
            startActivityForResult(i, 0);
        }
        if (id==R.id.accion_nuevo) {
            long id2 = Lugares.nuevo();
            Intent i= new Intent(this, EdicionLugar.class);
            i.putExtra("nuevo", true);
            i.putExtra("id", id2);
            startActivityForResult(i, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //super.onActivityResult(requestCode, resultCode, data);
        actualizarVistas();
    }

    public void onAboutClick(View view)
    {
        Intent intent = new Intent(this,About.class);
        startActivity(intent);
    }
   /* public void lanzarVistaLugar(View view){
        Intent i = new Intent(this, PoiEditorActivity.class);
        i.putExtra("id", (long)0);
        startActivity(i);
    }*/
    public void lanzarVistaLugar(View view){
       final EditText entrada = new EditText(this);
       entrada.setText("0");
       new AlertDialog.Builder(this)
               .setTitle("Selección de lugar")
               .setMessage("indica su id:")
               .setView(entrada)
               .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int whichButton) {
                       long id = Long.parseLong(entrada.getText().toString());
                       Intent i = new Intent(MainActivity.this, PoiEditorActivity.class);
                       i.putExtra("id", id);
                       startActivityForResult(i, 0);
                   }})

               .setNegativeButton("Cancelar", null)
               .show();
   }

    private void actualizaMejorLocaliz(Location localiz) {
        if (localiz == null) { return; }
        if (mejorLocaliz == null
                || localiz.getAccuracy() < 2*mejorLocaliz.getAccuracy()
                || localiz.getTime() - mejorLocaliz.getTime() > DOS_MINUTOS) {
            Log.d(Lugares.TAG, "Nueva mejor localización");
            mejorLocaliz = localiz;
            Lugares.posicionActual.setLatitud(localiz.getLatitude());
            Lugares.posicionActual.setLongitud(localiz.getLongitude());
        }
    }

    private void activarProveedores() {
        if(manejador.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            manejador.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    20 * 1000, 5, (LocationListener) this);
        }

        if(manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            manejador.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    10 * 1000, 10, (LocationListener) this);
        }
    }
   public void onExitClick(View view){
        finish();
    }
}
