package com.example.everton.horoscopo;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.everton.horoscopo.database.HoroscopoContract;
import com.example.everton.horoscopo.database.HoroscopoRepositorio;
import com.example.everton.horoscopo.fragments.HoroscopoDetailFragment;

public class HoroscopoDetailActivity extends AppCompatActivity {
HoroscopoRepositorio horoscopoRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscopo_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       horoscopoRepositorio = new HoroscopoRepositorio(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("Horosc");

        Cursor cursor = horoscopoRepositorio.consulta(HoroscopoContract._ID + " =" + id);
        cursor.moveToFirst();
        int icone = cursor.getInt(cursor.getColumnIndex(HoroscopoContract.COL_ICONE));
        String signo = cursor.getString(cursor.getColumnIndex(HoroscopoContract.COL_SUNSIGN));

        ImageView img = (ImageView) findViewById(R.id.detail_icone);
        CollapsingToolbarLayout collapser = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);


        Resources res = this.getResources();
        TypedArray logos = res.obtainTypedArray(R.array.logos);
        img.setImageDrawable(logos.getDrawable(icone));
        collapser.setTitle(signo);
    }
}
