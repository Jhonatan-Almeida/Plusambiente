package com.example.plusambiente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    EditText edtUsuario,edtPassword;
    Button btnLogin;

    String usuario, password,usu_id,usup_id,pef_id,tpef_id,per_nombres,per_identificacion;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        recuperarPreferencias();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = edtUsuario.getText().toString();//obtener el texto del editText
                password = edtPassword.getText().toString();//obtener el texto del editText
                if(!usuario.isEmpty() && !password.isEmpty()){
                    validarUsuario("http://144.91.69.179/plusambienteweb/servicios/cliente/login.php");
                }else{
                    //Toast.makeText(MainActivity.this,"Ingresar las credenciales.!!",Toast.LENGTH_SHORT).show();
                    Snackbar.make(v,"Ingresar las credenciales.!!", Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }
    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    guardarPreferencias();
                    buscarlogin("http://144.91.69.179/plusambienteweb/servicios/cliente/loginG.php?usuario="+usuario+"&password="+password);
                }else{
                    Toast.makeText(MainActivity.this,"Credenciales invalidas...!",Toast.LENGTH_SHORT).show();
                    //Snackbar.make(findViewById(R.id.Login) ,"Credenciales invalidas...!", Snackbar.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put( "usuario", usuario);
                parametros.put( "password", password);
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
   
    private void guardarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("prefereciasLogin",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("usuario",usuario);
        editor.putString("password",password);
        editor.putBoolean("sesion",true);
        editor.commit();
    }
    private void buscarlogin(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        usu_id= jsonObject.getString("usu_id");
                        usup_id = jsonObject.getString("usup_id");
                        pef_id = jsonObject.getString("pef_id");
                        per_nombres = jsonObject.getString("per_nombres");
                        tpef_id = jsonObject.getString("tpef_id");
                        per_identificacion = jsonObject.getString("per_identificacion");
                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent=new Intent(MainActivity.this,PrincipalActivity.class);
                intent.putExtra("usu_id",usu_id);
                intent.putExtra("usup_id",usup_id);
                intent.putExtra("pef_id",pef_id);
                intent.putExtra("per_nombres",per_nombres);
                intent.putExtra("per_identificacion",per_identificacion);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Existe algún error",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private void recuperarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("prefereciasLogin",Context.MODE_PRIVATE);
        edtUsuario.setText(preferences.getString("usuario",""));
        edtPassword.setText(preferences.getString("password",""));

    }
}