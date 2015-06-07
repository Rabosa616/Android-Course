package com.example.rabosa.pois;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Rabosa on 06/06/2015.
 */
public class EdicionLugar extends Activity {
    private long id;
    private Lugar lugar;
    private EditText nombre;
    private Spinner tipo;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poi_editor);
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
        lugar = Lugares.elemento((int) id);
        EditText nombre = (EditText) findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        Spinner tipo = tipo = (Spinner) findViewById(R.id.tipo);
        ArrayAdapter adaptador = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, TipoLugar.getNombres());
        adaptador.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        tipo.setAdapter(adaptador);
        tipo.setSelection(lugar.getTipo().ordinal());
        EditText direccion = (EditText) findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        EditText telefono = (EditText) findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        EditText url = (EditText) findViewById(R.id.url);
        url.setText(lugar.getUrl());
        EditText comentario = (EditText) findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edicion_lugar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accion_guardar:
                EditText nombre = (EditText) findViewById(R.id.nombre);
                lugar.setNombre(nombre.getText().toString());

                Spinner tipo = (Spinner) findViewById(R.id.tipo);
                lugar.setTipo(TipoLugar.values()[tipo.getSelectedItemPosition()]);

                EditText direccion = (EditText) findViewById(R.id.direccion);
                lugar.setDireccion(direccion.getText().toString());

                EditText telefono = (EditText) findViewById(R.id.telefono);
                lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));

                EditText url = (EditText) findViewById(R.id.url);
                lugar.setUrl(url.getText().toString());

                EditText comentario = (EditText) findViewById(R.id.comentario);
                lugar.setComentario(comentario.getText().toString());
                Lugares.actualizaLugar((int) id, lugar);
                finish();
            case R.id.accion_cancelar:
                finish();
        }

        return true;
    }

}
