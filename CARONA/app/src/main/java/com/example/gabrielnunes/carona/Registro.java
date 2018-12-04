package com.example.gabrielnunes.carona;


import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {
    final Context context = this;
    EditText nome, telefone, email, senha, confirmaSenha;
    CheckBox termos;
    TextView txttermos;
    Button cadastrar, condicoes;
    boolean flag= false;
    String url = "";
    String parametros = "";

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cadastrar = (Button) findViewById(R.id.cadastrar);




        cadastrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


            if(networkInfo != null && networkInfo.isConnected()){
                url = "http://www.kdcarona.16mb.com/cadastro2.php";

                TextView nome = (TextView) findViewById(R.id.nome);
                TextView telefone = (TextView) findViewById(R.id.telefone);
                TextView email = (TextView) findViewById(R.id.email);
                TextView senha = (TextView) findViewById(R.id.senha);
                TextView confirmaSenha = (TextView) findViewById(R.id.confirmaSenha);
                

                String nome1=nome.getText().toString();
                String telefone1=telefone.getText().toString();
                String email1=email.getText().toString();
                String senha1=senha.getText().toString();
                String confirmaSenha1 = confirmaSenha.getText().toString();

                boolean validarEmail = isEmailValid(email.getText().toString());

                if (nome1.isEmpty() || telefone1.isEmpty() || email1.isEmpty() || senha1.isEmpty() || confirmaSenha1.isEmpty() || validarEmail==false ){
                    Toast.makeText(getApplicationContext(), "Você não preencheu um ou mais campos corretamente", Toast.LENGTH_LONG).show();
                }else
                {
                    if(senha1.equals(confirmaSenha1))
                    {
                        parametros = "&nome="+nome1+"&telefone="+telefone1+"&email="+email1+"&senha="+senha1;
                        new Registro.SolicitaDados().execute(url);
                    }else
                    {
                        Toast.makeText(getApplicationContext(), "As senhas digitadas não conferem", Toast.LENGTH_LONG).show();
                    }
                }
            }else{
                Toast.makeText(getApplicationContext(), "Não foi detectado conexão com a internet", Toast.LENGTH_LONG).show();
            }
        }
        });
    }
      class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0], parametros);
        }
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "Cadastro realizado", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Registro.this, Tela_inicial.class);
            startActivity(intent);
        }
    }
}