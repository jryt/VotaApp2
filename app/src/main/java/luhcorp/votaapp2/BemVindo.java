package luhcorp.votaapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import luhcorp.votaapp2.uteis.Global;

public class BemVindo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        //apenas um teste para verificar se a variavel global com o numero do titulo do cidadao logado funciona
        Global variaveisGlobais = (Global)getApplicationContext();
        //aqui vai colocar o numeroTitulo em uma variavel global da aplicacao
        int numeroTitulo = variaveisGlobais.getNumeroTitulo();
        Toast.makeText(BemVindo.this,"Este é o titulo do usuario logado: "+numeroTitulo,
                Toast.LENGTH_LONG).show();
    }
}
