package luhcorp.votaapp2.uteis;

import android.app.Application;

/**
 * Created by LuizH on 27/11/2016.
 */
public class Global extends Application{
    //variavel global que guarda o numero do titulo do usuario logado
    private int numeroTitulo;

    public int getNumeroTitulo() {
        return numeroTitulo;
    }

    public void setNumeroTitulo(int numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }
}
