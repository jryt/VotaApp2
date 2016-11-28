package luhcorp.votaapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import luhcorp.votaapp2.entidades.Login;
import luhcorp.votaapp2.uteis.Global;

public class MainActivityLogin extends AppCompatActivity implements Response.Listener,Response.ErrorListener{

    public static final String REQUEST_TAG = "LoginTag";
    private RequestQueue myRequestQueue;

    //variavei globais para mapeamento de componentes da view de login
    private EditText campoNumeroTitulo;
    private EditText campoSenha;
    private Button botaoLogin;

    //define a url a ser chamada
    private String url = "http://10.0.2.2:8080/votar/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);

        //mapeando os componentes view para usar dentro dessa classe
        campoNumeroTitulo = (EditText)findViewById(R.id.campoNumeroTitulo);
        campoSenha = (EditText)findViewById(R.id.campoSenha);
        botaoLogin = (Button)findViewById(R.id.loginButton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //inicializa o request queue
        myRequestQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

        //criando o listener do botao
        //ao ser clicado teremos
        //preencher o json para envio com os dados dos campos
        //colocar o json na request
        //colocar o json na requestQueue (minha fila de requests)
        botaoLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (campoNumeroTitulo == null || campoSenha == null) {
                            Toast.makeText(MainActivityLogin.this, "Favor preencher titulo e senha!!!",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            JSONObject json = createJsonEnvio(campoNumeroTitulo, campoSenha);
                            final CustomJSONObjectRequest request = new CustomJSONObjectRequest(
                                    Request.Method.POST, url, json, MainActivityLogin.this, MainActivityLogin.this);
                            request.setTag(REQUEST_TAG);
                            //adiciona a request a fila de requests para execucao
                            myRequestQueue.add(request);

                        }

                    }
                }

        );


    }

    @Override
    protected void onStop(){
        super.onStop();
        if(myRequestQueue!=null){
            myRequestQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(MainActivityLogin.this, "Erro no serviço!!!",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(Object response) {
        try {
            String resposta = (String) ((JSONObject) response).get("status");
            if("denied".equals(resposta)){
                Toast.makeText(MainActivityLogin.this, "Falha no login, verifique se o titulo e senha estão corretos.",
                        Toast.LENGTH_LONG).show();
            }else if("allowed".equals(resposta)){
                Global variaveisGlobais = (Global)getApplicationContext();
                //aqui vai colocar o numeroTitulo em uma variavel global da aplicacao
                variaveisGlobais.setNumeroTitulo(Integer.parseInt(campoNumeroTitulo.getText().toString()));
                //aqui vai a intent para a tela de boas vindas
                Intent intent = new Intent(MainActivityLogin.this,BemVindo.class);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivityLogin.this,resposta,
                        Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private JSONObject createJsonEnvio(EditText campoNumeroTitulo,EditText campoSenha){
        //fazer o parse dos elementos para mandar no json
        int titulo = Integer.parseInt(campoNumeroTitulo.getText().toString());
        int senha = Integer.parseInt(campoSenha.getText().toString());

        //montando json para envio
        JSONObject json = new JSONObject();

        try {
            //json.put("login",new Login(titulo,senha));
            json.put("titulo",titulo);
            json.put("senha",senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }



}
