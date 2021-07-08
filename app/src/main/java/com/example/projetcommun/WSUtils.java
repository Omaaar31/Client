package com.example.projetcommun;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

public class WSUtils {

    public static PointBean loadWeather(double latitude, double longitude) throws Exception {
        String url = "&lat=" + latitude + "&lon=" + longitude;
        //Requete
        String json = OkhttpUtils.sendGetOkHttpRequest(url);
        //Parser le JSON
        System.out.println("Json = " + json);
        PointBean weatherBean = new Gson().fromJson(json, PointBean.class);
        return weatherBean;
    }

    public static LatLng getISSPosition() throws Exception {
        //Requete
        String json = OkhttpUtils.sendGetOkHttpRequest();
        //Parser le JSON
        System.out.println("Json = " + json);
        ISSWSResponseBean isswsResponseBean = new Gson().fromJson(json, ISSWSResponseBean.class);
        if (isswsResponseBean.getIss_position() == null
                || isswsResponseBean.getIss_position().latitude == 0
                || isswsResponseBean.getIss_position().longitude == 0) {
            throw new Exception("Pas de position");
        }

        return isswsResponseBean.getIss_position();
    }

}
