package luhcorp.votaapp2.entidades;

/**
 * Created by LuizH on 27/11/2016.
 */
public class Login {

    private int titulo;
    private int senha;

    public Login(int titulo, int senha) {
        this.titulo = titulo;
        this.senha = senha;
    }

    public int getTitulo() {
        return titulo;
    }

    public void setTitulo(int titulo) {
        this.titulo = titulo;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
}
