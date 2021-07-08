package com.projetandroid.touradvisor;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projetandroid.OkhttpUtils;
import com.projetandroid.touradvisor.beans.PointBean;

import java.util.ArrayList;
import java.util.List;

public class WSUtils {

    private static final String URL_GENERIQUE ="http://192.168.10.88:8080/";


    public static String test() throws Exception {
        String url = URL_GENERIQUE+ "/testPoint";
        String json = OkhttpUtils.sendPostOkHttpRequest(url);

        return json;
    }

    public static List<PointBean> getPoints() throws Exception {
        Gson gson = new Gson();
        String url = URL_GENERIQUE+ "/getPoints";
        //Requete
        String json = OkhttpUtils.sendPostOkHttpRequest(url);
        //Parser le JSON
        System.out.println("Json = " + json);
        ArrayList<PointBean> list = gson.fromJson(json, new TypeToken<ArrayList<PointBean>>(){}.getType());

        return list;
    }
}
