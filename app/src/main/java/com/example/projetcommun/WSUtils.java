package com.example.projetcommun;

import android.graphics.Point;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class WSUtils {

    private static final String URL_WS = "http://192.168.10.13:8080/test";

   private static final String URL_SERVER =  "http://192.168.10.13:8080";

    public static void test() throws Exception {
        String url =URL_SERVER + "/test" ;
        //Requete
        String reponse = OkHttpUtilsKt.sendGetOkHttpRequest(url);
        //Parser le JSON
        System.out.println("reponse= " + reponse);

    }


    public static PointBean loadPoint(double latitude, double longitude) throws Exception {
        String url = "&lat=" + latitude + "&lon=" + longitude;
        //Requete
        String json = OkHttpUtilsKt.sendGetOkHttpRequest(url);
        //Parser le JSON
        System.out.println("Json = " + json);
        PointBean pointBean = new Gson().fromJson(json, PointBean.class);
        return pointBean;
    }

    public static void retest() {
        Gson gson = new Gson();
        ArrayList<PointBean> list = gson.fromJson("mon json", new TypeToken<ArrayList<PointBean>>() {
        }.getType());
    }

//    public static LatLng getMapPosition() throws Exception {
//        //Requete
//        String json = OkHttpUtilsKt.sendGetOkHttpRequest(URL_WS_ISS);
//        //Parser le JSON
//        System.out.println("Json = " + json);
//        PointBean pointBean = new Gson().fromJson(json, PointBean.class);
//        if (PointBean.() == null
//                || PointBean.getIss_position().latitude == 0
//                || PointBean.getIss_position().longitude == 0) {
//            throw new Exception("Pas de position");
//        }
//
//        return isswsResponseBean.getIss_position();
//    }
}
