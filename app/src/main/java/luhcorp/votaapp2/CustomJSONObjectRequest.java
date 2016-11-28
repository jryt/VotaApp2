package luhcorp.votaapp2;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LuizH on 27/11/2016.
 */
public class CustomJSONObjectRequest extends JsonObjectRequest{


    //construtor recebe a o método a ser chamado no ws a url para requisicao
    //o json que desejo enviar
    //um listener para receber o json de sucesso na resposta
    //um listener para receber o erro na resposta
    public CustomJSONObjectRequest(int method, String url, JSONObject jsonRequest,
                                   Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        super(method, url, jsonRequest, listener, errorListener);
    }

    //sobrescreve o método retornando header padrao de Json
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json; charset=utf-8");

        return headers;
    }

    //politica customizada de reversao
    @Override
    public RetryPolicy getRetryPolicy() {
        return super.getRetryPolicy();
    }
}
