package com.example.projetcommun;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class WSUtils {

   private static final String URL_SERVER =  "http://192.168.10.13:8080";

    public static void test() throws Exception {
        String url =URL_SERVER + "/test" ;
        //Requete
        String reponse = OkHttpUtilsKt.sendGetOkHttpRequest(url);
        //Parser le JSON
        System.out.println("reponse= " + reponse);

    }



    //Envoyer un point
    public static void sendPoint(PointBean pointBean) throws Exception {
        String url = URL_SERVER +  "/setPoints";
        //Requete
       String jsonAEnvoyer =  new Gson().toJson(pointBean);
       OkHttpUtilsKt.sendPostOkHttpRequest(url, jsonAEnvoyer);
    }

    //Je reçois un point
    public static ArrayList<PointBean> getPoints() throws Exception {
        String url = URL_SERVER +  "/getPoints";


      String jsonRecu =   OkHttpUtilsKt.sendGetOkHttpRequest(url);
      //Je convertie json en arrayList
      Gson gson = new Gson();
        ArrayList<PointBean> list  = gson.fromJson(jsonRecu, new  TypeToken<ArrayList<PointBean>>(){}.getType());
        return list;
    }

}
