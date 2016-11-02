package com.example.everton.horoscopo.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.everton.horoscopo.MainActivity;
import com.example.everton.horoscopo.R;

/**
 * Created by Everton on 11/09/2016.
 */
public class VerificaConexão {

    public VerificaConexão(){}

    public boolean verifica(Context v){

        try {

            ConnectivityManager cm = (ConnectivityManager) v.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                Log.d("NGVL", "Conectado");
                return true;
            } else {

                Toast.makeText(v, "Não há conexão,", Toast.LENGTH_SHORT).show();
                return false;
            }
        }catch (Exception e){

            Toast.makeText(v, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("Erro: ", e.getMessage());
            return false;

        }



    }

}
