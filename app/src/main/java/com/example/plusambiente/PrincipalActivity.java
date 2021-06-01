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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class PrincipalActivity extends AppCompatActivity {
    Button btnCerrar;
    Bundle datos;
    TextView txtUsuario;
    ArrayList<odt> listaOdt;
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
            listaOdt = new ArrayList<odt>();
            obtenerodt("http://192.168.100.25/plusambiete2/servicios/cliente/ordenesTrabajo.php?identificacion="+identificacion);
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

                                listaOdt.add(new odt(auxSolicitud, auxManifiesto, auxCliente, auxTecnico, auxConductor));
                            }
                            AdapterOdt adaptador = new AdapterOdt(listaOdt);
                            adaptador.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent=new Intent(PrincipalActivity.this,DesechoActivity.class);
                                    intent.putExtra("sol_id",listaOdt.get
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

}