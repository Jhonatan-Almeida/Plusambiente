package com.example.plusambiente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCerrar;
    Bundle datos;
    TextView txtUsuario;
    ArrayList<odtEntity> listaOdtEntity;
    RecyclerView recyclerOdt;
    RequestQueue requestQueue;
    String identificacion,nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        btnCerrar = findViewById(R.id.btnCerrar);
        txtUsuario = (TextView) findViewById(R.id.textUsuario);
        //tomamos los datos del intent
        datos = getIntent().getExtras();
        if (datos != null){
            nombre = datos.getString("per_nombres");
            identificacion = datos.getString("per_identificacion");
            txtUsuario.setText(nombre);
            recyclerOdt = (RecyclerView) findViewById(R.id.rvOdt);
            recyclerOdt.setLayoutManager(new GridLayoutManager(this, 1));
            listaOdtEntity = new ArrayList<odtEntity>();
            obtenerodt("http://144.91.69.179/plusambienteweb/servicios/cliente/ordenesTrabajo.php?identificacion="+identificacion);
        }


        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences=getSharedPreferences("prefereciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        txtUsuario.setOnClickListener(PrincipalActivity.this);
    }
    public void obtenerodt(String URL) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,
                new Response.Listener   <String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                             JSONObject jsonObject = new JSONObject(response);
                             JSONArray jsonArray = jsonObject.getJSONArray("odt");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                String auxSolicitud = jsonObject1.getString("sol_id");
                                String auxManifiesto = jsonObject1.getString("sol_manifiesto");
                                String auxCliente = jsonObject1.getString("cli_nombre");
                                String auxTecnico = jsonObject1.getString("tec_nombre");
                                String auxConductor = jsonObject1.getString("conductor");

                                listaOdtEntity.add(new odtEntity(auxSolicitud, auxManifiesto, auxCliente, auxTecnico, auxConductor));
                            }
                            AdapterOdt adaptador = new AdapterOdt(listaOdtEntity);
                            adaptador.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent=new Intent(PrincipalActivity.this,DetalleActivity.class);
                                    intent.putExtra("sol_id", listaOdtEntity.get
                                            (recyclerOdt.getChildAdapterPosition(v))
                                            .getSol_id());
                                    intent.putExtra("per_identificacion",identificacion);
                                    intent.putExtra("per_nombres",nombre);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            recyclerOdt.setAdapter(adaptador);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(v,"El conductor tiene el número de cédula "+identificacion,Snackbar.LENGTH_LONG).show();
        //Toast.makeText(v.getContext(),"El conductor tiene el número de cédula "+identificacion,Toast.LENGTH_SHORT).show();
    }
}