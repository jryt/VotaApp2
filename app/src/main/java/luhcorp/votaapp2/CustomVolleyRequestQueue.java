package luhcorp.votaapp2;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by LuizH on 27/11/2016.
 */
public class CustomVolleyRequestQueue {
    private static CustomVolleyRequestQueue myInstance;
    private static Context myContext;
    private RequestQueue myRequestQueue;

    //esta classe garante uma instancia de CustomVolleyRequestQueue para toda a aplicacao
    //um contexto para toda a aplicacao
    //uma requestQueue para toda a aplicacao
    //por isso as variaveis sao estaticas e só sao incicializadas caso sejam nulas


    //Similar a um construtor recebe um context e retorna uma instancia de CustomRequestQueue
    private CustomVolleyRequestQueue(Context context){
        //Recebe o contexto da aplicação e seta na variavel estatica ou seja uma instancia compartilhada para todos
        myContext = context;
        myRequestQueue = getRequestQueue();
    }
    //Inicializa uma instancia estatica de CustomVolleyRequestQueue
    public static synchronized CustomVolleyRequestQueue getInstance(Context context){
        if(myInstance==null){
            myInstance = new CustomVolleyRequestQueue(context);
        }
        return myInstance;
    }

    //inicializa e retorna uma requestQueue statica com cache de 10MB
    public RequestQueue getRequestQueue(){
        //se requestQueue for nula cria a instancia
        if(myRequestQueue==null){
            //cache de 10Mb
            Cache cache = new DiskBasedCache(myContext.getCacheDir(),10*1024*1024);
            Network network = new BasicNetwork(new HurlStack());
            myRequestQueue = new RequestQueue(cache,network);
            myRequestQueue.start();
        }
        return myRequestQueue;
    }
}
