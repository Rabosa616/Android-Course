package com.example.rabosa.pois;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.poi_editor);
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

        return super.onOptionsItemSelected(item);
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
                       startActivity(i);
                   }})

               .setNegativeButton("Cancelar", null)
               .show();
   }
    public void onExitClick(View view){
        finish();
    }
}
