package com.example.everton.horoscopo.http;

import android.content.Context;
import android.widget.Toast;

import com.example.everton.horoscopo.model.Horoscopo;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Everton on 11/09/2016.
 */
public class HoroscopoHttp {

    public static final String API_URL =  "http://theastrologer-api.herokuapp.com/api/horoscope/%s/today";
    //public static final String API_URL =  "http://theastrologer-api.herokuapp.com/api/horoscope/%s";

    public static Horoscopo carregarHoroscopo(String query){


        try {
        OkHttpClient client = new OkHttpClient();
        String url = String.format(API_URL, query);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;

            response = client.newCall(request).execute();
            String json =  response.body().string();
            Gson gson = new Gson();
            Horoscopo horoscopo = gson.fromJson(json, Horoscopo.class);
            return horoscopo;

        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
