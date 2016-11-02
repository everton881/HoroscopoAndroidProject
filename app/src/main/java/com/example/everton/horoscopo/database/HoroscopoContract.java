package com.example.everton.horoscopo.database;

import android.provider.BaseColumns;

/**
 * Created by Everton on 11/09/2016.
 */
public interface HoroscopoContract extends BaseColumns {
    String TABLE_NAME = "horoscopo";


    String COL_MESSAGEM = "horoscope";
    String COL_DATA     = "date";
    String COL_SUNSIGN    = "sunsigne";
   /* String COL_INTENSITY = "intensity";
    String COL_KEYWORDS  = "keywords";
    String COL_MOD       = "mood";*/
    String COL_ICONE     = "icone";

    String[] ALL_COLUMNS = new String[] {
            _ID          ,
            COL_MESSAGEM ,
            COL_DATA     ,
            COL_SUNSIGN    ,
           /* COL_INTENSITY,
            COL_KEYWORDS ,
            COL_MOD      ,*/
            COL_ICONE
 };

}