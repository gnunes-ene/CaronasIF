package com.example.gabrielnunes.carona;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    int flag;
    EditText email, senha;
    Button login;
    String url = "";
    String parametros="";
    public static String aux = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.Login);

         email = findViewById(R.id.email);
         senha = findViewById(R.id.senha);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    public void  login ()
    {
        StringRequest request = new StringRequest(Request.Method.POST, "http://www.kdcarona.16mb.com/teste_login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Usuário e/ou Senha incorretos ", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(getApplicationContext(),MenuInferior.class));
                    aux= response.trim();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", email.getText().toString());
                params.put("senha", senha.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
