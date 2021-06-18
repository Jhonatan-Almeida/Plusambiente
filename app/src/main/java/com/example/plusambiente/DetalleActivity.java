package com.example.plusambiente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetalleActivity extends AppCompatActivity implements View.OnClickListener{
    ListView lista;
    ArrayList<desechosEntity> desechosEntityList;
    //ArrayList<desechos> listaDesecho;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    Bundle datos;
    FloatingActionButton btnRegresar,btnGuardar;
    String sol_id,per_nombres,per_identificacion;
    AdapterDetalle adapterDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnRegresar = findViewById(R.id.btnRegresar);
        //obtenemos el numero de solicitud del PrincipalActivity
        datos = getIntent().getExtras();
        sol_id = datos.getString("sol_id");
        per_nombres = datos.getString("per_nombres");
        per_identificacion = datos.getString("per_identificacion");
        // 1.- Conectamos nuestro elemento
        recyclerView = findViewById(R.id.rvDetalle);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        //2.- Cargar la lista de elementos
        desechosEntityList = new ArrayList<desechosEntity>();
        obteneDesechos("http://malta1481.startdedicated.com:8086/plusambiete2/servicios/cliente/listaDesechos.php?sol_id="+sol_id);
        adapterDetalle = new AdapterDetalle(desechosEntityList);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"boton de regresar",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),PrincipalActivity.class);
                intent.putExtra("per_identificacion",per_identificacion);
                intent.putExtra("per_nombres",per_nombres);
                startActivity(intent);
                finish();
            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Se ha recuperado los datos",Snackbar.LENGTH_LONG).show();
                  String[] cantidades = adapterDetalle.getCantidades();
                  for(int i = 0; i< cantidades.length;i++){
                      System.out.println(cantidades[i]);
                  }
            }
        });
    }
    public void obteneDesechos(String URL) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("desecho");
                            desechosEntityList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                String auxSolicitud = jsonObject1.getString("sol_id");
                                String dsol_id = jsonObject1.getString("dsol_id");
                                String des_codigo = jsonObject1.getString("des_codigo");
                                String des_descripcion = jsonObject1.getString("des_descripcion");
                                String cat_unidad = jsonObject1.getString("cat_unidad");
                                String cat_peligroso = jsonObject1.getString("cat_peligroso");
                                String dsol_cantidad = jsonObject1.getString("dsol_cantidad");
                                //String sol_id, String des_codigo, String des_descripcion, String cat_unidad, String cat_peligroso, String dsol_cantidad,String dsol_id

                                desechosEntityList.add(new desechosEntity(auxSolicitud, des_codigo, des_descripcion, cat_unidad,cat_peligroso, dsol_cantidad,dsol_id));
                            }
                            adapterDetalle = new AdapterDetalle(desechosEntityList);
                            /*adapterDetalle.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });*/
                            recyclerView.setAdapter(adapterDetalle);;

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
        Snackbar.make(v,"Este es el cuerpo de el desecho ",Snackbar.LENGTH_LONG).show();
    }
}