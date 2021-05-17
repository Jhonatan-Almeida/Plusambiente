package com.example.plusambiente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity {
    Button btnCerrar;
    Bundle datos;
    TextView txtUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        btnCerrar = findViewById(R.id.btnCerrar);
        txtUsuario = (TextView) findViewById(R.id.textUsuario);
        //tomamos los datos del intent
        datos = getIntent().getExtras();
        if (datos != null){
            String nombre = datos.getString("per_nombres");
            txtUsuario.setText(nombre);
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
}