package com.gestionarriendo.gva.natillera;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ControlPagos extends AppCompatActivity {
    EditText id, fecha, usuarioId, valor;
    Button registrar;
    RequestQueue requestQueue;
    private static final String url="http://natillerabackend.azurewebsites.net/api/ControlPagos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_pagos);
        usuarioId = (EditText)findViewById(R.id.usuarioIdpago);
        fecha = (EditText)findViewById(R.id.fecha);
        valor = (EditText)findViewById(R.id.valorpago);
        id = (EditText)findViewById(R.id.idpagos);
        requestQueue= Volley.newRequestQueue(this);
        registrar = (Button)findViewById(R.id.ingreso);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setJsonDataToWebservices(url);
            }
        });
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
    }

    public void setJsonDataToWebservices(String url){
        JSONObject params = new JSONObject();
        try {
            params.put("$id", id.getText().toString());
            params.put("Id",id.getText().toString());
            params.put("Fecha",fecha.getText().toString());
            params.put("Valor", valor.getText().toString());
            params.put("UsuarioID", usuarioId.getText().toString());
            limpiarcampos();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Create a future request object

        // Create a json request object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getApplicationContext(),"Sus datos se enviaron",Toast.LENGTH_LONG);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new  HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);


    }

    public void limpiarcampos()
    {
        id.setText("");
        fecha.setText("");
        usuarioId.setText("");
        valor.setText("");
    }
}
