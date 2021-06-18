package com.example.plusambiente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DesechoActivity extends AppCompatActivity {
    Bundle datos;
    FloatingActionButton btnRegresar,btnGuardar;
    String sol_id,per_nombres,per_identificacion;
    List<desechosEntity> desechosEntityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desecho);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnRegresar = findViewById(R.id.btnRegresar);
        //obtenemos el numero de solicitud del PrincipalActivity
        datos = getIntent().getExtras();
        sol_id = datos.getString("sol_id");
        per_nombres = datos.getString("per_nombres");
        per_identificacion = datos.getString("per_identificacion");
        //Enviar al Fragment
        Bundle dat = new Bundle();
        dat.putString("sol_id",sol_id);
        DesechosFragment desechosFragment= new DesechosFragment();
        desechosFragment.setArguments(dat);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentDesechos,desechosFragment)
                .commit();
       // MyDesechosRecyclerViewAdapter adaptador = new MyDesechosRecyclerViewAdapter(desechosList);

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
                   /* ArrayList<String> cantidad = new ArrayList<String>();
                    String[] desecho= adaptador.getCantidades();
                    for (int i=0;i<desecho.length;i++){
                        cantidad.add(desecho[i]);
                    }
                    System.out.println(cantidad);*/
            }
        });
    }


}