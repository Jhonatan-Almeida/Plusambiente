package com.example.plusambiente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DesechoActivity extends AppCompatActivity {
    Bundle datos;
    Button btnGuardar,btnRegresar;
    String sol_id,per_nombres,per_identificacion;
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
        DesechosFragment desechosFragment=new DesechosFragment();
        desechosFragment.setArguments(dat);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentDesechos,desechosFragment)
                .commit();
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
    }


}