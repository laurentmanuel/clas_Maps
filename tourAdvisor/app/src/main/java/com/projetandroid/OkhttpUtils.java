package com.projetandroid;

import android.util.Log;

import com.projetandroid.touradvisor.beans.ErrorBean;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {

    private static int CODE_ERROR_RETOUR = 518;
    private static int CODE_ERROR_MIN = 200;
    private static int CODE_ERROR_MAX = 300;



    public static String sendPostOkHttpRequest(String url) throws Exception {

        Log.w("tag", "url : " + url);
        OkHttpClient client = new OkHttpClient();
        //Création de la requete
        Request request = new Request.Builder().url(url).build();
        //Execution de la requête
        Response response = client.newCall(request).execute();
        //Analyse du code retour
        if(response.code() == CODE_ERROR_RETOUR) {
          ErrorBean error = new Gson().fromJson(response.body().string(), ErrorBean.class);
          throw new Exception(error.getMessage());
        }
        if (response.code() < CODE_ERROR_MIN || response.code() >= CODE_ERROR_MAX) {
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        } else {
            //Résultat de la requete.
            //ATTENTION .string() ne peut être appelée qu’une seule fois.
            return response.body().string();
        }
    }
}
