package com.gestionarriendo.gva.natillera;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String>lista= new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private String username;
    private ArrayList<String> listaMenu= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listView=(ListView) findViewById(R.id.lista);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, lista);
        listView.setAdapter(arrayAdapter);
        lista.add("Insertar Usuario");
        lista.add("Insertar Prestamo");
        lista.add("Insertar Pagos");
        lista.add("Consultar Usuario");
        lista.add("Consultar Prestamo");
        lista.add("Consultar Pagos");
        arrayAdapter.notifyDataSetChanged();
        solicitaruername();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position)
                {
                    case 0: intent = new Intent(getApplicationContext(), Usuarios.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                        break;
                    case 1: intent = new Intent(getApplicationContext(), Prestamos.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                        break;
                    case 2: intent = new Intent(getApplicationContext(), ControlPagos.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                        break;

                }

            }
        });
    }


    private void solicitaruername() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ingresar su usuario");
        final EditText ingresar = new EditText(this);
        builder.setView(ingresar);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                username=ingresar.getText().toString();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();;
                solicitaruername();
            }
        });
        builder.show();
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

        return super.onOptionsItemSelected(item);
    }
}
