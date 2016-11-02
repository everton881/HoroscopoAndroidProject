package com.example.everton.horoscopo;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.example.everton.horoscopo.database.HoroscopoContract;
import com.example.everton.horoscopo.database.HoroscopoRepositorio;
import com.example.everton.horoscopo.fragments.HoroscopoDetailFragment;

public class MainActivity extends AppCompatActivity implements OnHoroscopoClickLlistener {


    View frag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setIcon(R.mipmap.ic2);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private boolean istablet() {
        return getResources().getBoolean(R.bool.tablet);

    }

    private boolean isSmartphone() {
        return getResources().getBoolean(R.bool.smartphone);
    }

    @Override
    public void onMovieClick (long id){
        if (isSmartphone()) {
            Intent it = new Intent(MainActivity.this, HoroscopoDetailActivity.class);
            it.putExtra("Horosc", String.valueOf(id));
            startActivity(it);


        } else {

            frag = findViewById(R.id.fragmentDetail);
           HoroscopoDetailFragment horoscopoDetailFragment= HoroscopoDetailFragment.newInstance(id);
            horoscopoDetailFragment.teste(true);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentDetail, horoscopoDetailFragment)
                    .show(horoscopoDetailFragment)
                    .commit();



        }
    }
}

