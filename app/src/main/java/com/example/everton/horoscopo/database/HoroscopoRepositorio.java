package com.example.everton.horoscopo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import com.example.everton.horoscopo.model.Horoscopo;

/**
 * Created by everton.vasconcelos on 13/09/2016.
 */
public class HoroscopoRepositorio {

    private Context cx;

    public HoroscopoRepositorio(Context ctx){

        this.cx = ctx;
    }

    public void inserir(Horoscopo horoscopos) {

        if (horoscopos != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(HoroscopoContract.COL_DATA, horoscopos.getData());
            contentValues.put(HoroscopoContract.COL_MESSAGEM, horoscopos.getMessagem());
            contentValues.put(HoroscopoContract.COL_SUNSIGN, horoscopos.getSigno());
          /*  contentValues.put(HoroscopoContract.COL_INTENSITY, horoscopos.getIntensity());
            contentValues.put(HoroscopoContract.COL_KEYWORDS, horoscopos.getKeywords());
            contentValues.put(HoroscopoContract.COL_MOD, horoscopos.getMood());*/
            contentValues.put(HoroscopoContract.COL_ICONE, horoscopos.getIcone());
            cx.getContentResolver().insert(HoroscopoProvider.HOROSCOPO_URI, contentValues);
        }
    }

    public void adicionarIcone(String sig, int ii){

        ContentValues contentUpdate = new ContentValues();

                contentUpdate.put(HoroscopoContract.COL_ICONE, ii);
                int afetado = cx.getContentResolver().update(HoroscopoProvider.HOROSCOPO_URI,
                        contentUpdate,
                        HoroscopoContract.COL_SUNSIGN + "='" + sig + "'",
                        null);
    }

    public CursorLoader buscar(String[] selectt, String where){

       return new CursorLoader(cx,
               HoroscopoProvider.HOROSCOPO_URI,
               selectt,
               where,
               null,
               null);

    }

    public Cursor consulta(String where){
        Cursor cursor = cx.getContentResolver().query(HoroscopoProvider.HOROSCOPO_URI,
                HoroscopoContract.ALL_COLUMNS,
                where,
                null,
                null);
        return cursor;

    }

}
