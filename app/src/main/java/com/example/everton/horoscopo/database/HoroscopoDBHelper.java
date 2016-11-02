package com.example.everton.horoscopo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Everton on 11/09/2016.
 */
public class HoroscopoDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Horoscopodb";
    private static final int DB_VERSION = 1;

    public HoroscopoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(
                    "CREATE TABLE " + HoroscopoContract.TABLE_NAME + " (" +
                            HoroscopoContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            HoroscopoContract.COL_MESSAGEM + " TEXT NOT NULL, " +
                            HoroscopoContract.COL_DATA + " TEXT NOT NULL, " +
                            HoroscopoContract.COL_SUNSIGN + " TEXT UNIQUE NOT NULL, " +
                           /* HoroscopoContract.COL_INTENSITY + " TEXT," +
                            HoroscopoContract.COL_KEYWORDS + " TEXT, " +
                            HoroscopoContract.COL_MOD + " TEXT, " +*/
                            HoroscopoContract.COL_ICONE + " INTEGER);");


        }catch (Exception e){
            Log.e("Erro Banco: ", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
