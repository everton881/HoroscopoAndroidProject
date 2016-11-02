package com.example.everton.horoscopo;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.everton.horoscopo.database.HoroscopoContract;
import com.example.everton.horoscopo.database.HoroscopoProvider;
import com.example.everton.horoscopo.database.HoroscopoRepositorio;
import com.example.everton.horoscopo.http.HoroscopoHttp;
import com.example.everton.horoscopo.http.VerificaConexão;
import com.example.everton.horoscopo.model.Horoscopo;

/**
 * Created by everton.vasconcelos on 16/09/2016.
 */
public class HoroscopoJsonbanco {

    Context ctx;
    HoroscopoRepositorio horoscopoRepositorio;
     public HoroscopoJsonbanco(Context ctx){
        this.ctx = ctx;
    }

    class HoroscopoTsk extends AsyncTask<String, Void, Horoscopo> {

        @Override
        protected Horoscopo doInBackground(String... params) {
            return HoroscopoHttp.carregarHoroscopo(params[0]);
        }

        protected void onPostExecute(Horoscopo horoscopos){
            super.onPostExecute(horoscopos);
            String verificaMsg = null;
            String verificaData = null;
            int verificaIcone = 0;

            ContentValues contentUpdate = new ContentValues();
            horoscopoRepositorio = new HoroscopoRepositorio(ctx);

               Cursor cursor = horoscopoRepositorio.consulta(null);

            if(cursor.getCount() != 12) {
                horoscopoRepositorio.inserir(horoscopos);
                Cursor cursor3 = horoscopoRepositorio.consulta(HoroscopoContract.COL_SUNSIGN + " = '" + horoscopos.getSigno() +"'");

                while(cursor3.moveToNext()) {
                   verificaIcone = cursor3.getInt(cursor3.getColumnIndex(HoroscopoContract._ID));
                }
                verificaIcone = verificaIcone - 1 ;
                horoscopoRepositorio.adicionarIcone(horoscopos.getSigno(), verificaIcone);

                cursor3.close();
            }else {

                  Cursor cursor3 = horoscopoRepositorio.consulta(HoroscopoContract.COL_SUNSIGN + " = '" + horoscopos.getSigno() + "'");
                  cursor3.moveToFirst();
                  verificaMsg = cursor3.getString(cursor3.getColumnIndex(HoroscopoContract.COL_MESSAGEM));
                  verificaData = cursor3.getString(cursor3.getColumnIndex(HoroscopoContract.COL_DATA));
                  cursor3.close();

                 if(!verificaMsg.equals(horoscopos.getMessagem()) || !verificaData.equals(horoscopos.getData())) {
                     contentUpdate.put(HoroscopoContract.COL_MESSAGEM, horoscopos.getMessagem());
                     contentUpdate.put(HoroscopoContract.COL_DATA, horoscopos.getData());

                     ctx.getContentResolver().update(HoroscopoProvider.HOROSCOPO_URI,
                            contentUpdate,
                            HoroscopoContract.COL_SUNSIGN + "='" + horoscopos.getSigno() + "'",
                            null);
                }

                cursor3.close();
            }
            cursor.close();
        }

    }

    public void inserirHoroscopo(){

        String[] array = ctx.getResources().getStringArray(R.array.horosc_array);
        VerificaConexão verificaConexão = new VerificaConexão();
        boolean conexao = verificaConexão.verifica(ctx);
        if(conexao == true) {
            for (int i = 0; i < array.length; i++) {
                new HoroscopoTsk().execute(array[i]);
            }
        }

    }

}
