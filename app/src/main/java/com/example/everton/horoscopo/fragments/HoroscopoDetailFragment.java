package com.example.everton.horoscopo.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.everton.horoscopo.R;
import com.example.everton.horoscopo.database.HoroscopoContract;
import com.example.everton.horoscopo.database.HoroscopoRepositorio;

/**
 * Created by everton.vasconcelos on 23/09/2016.
 */
public class HoroscopoDetailFragment extends Fragment {

    HoroscopoRepositorio horoscopoRepositorio;
    String idd;
    boolean vdfs;
    String titulo;
    int icone;
    String msg;
    String data;
    public static HoroscopoDetailFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putString("Horosc", String.valueOf(id));
        HoroscopoDetailFragment horoscopoDetailFragment= new HoroscopoDetailFragment();
        horoscopoDetailFragment.setArguments(args);
        return horoscopoDetailFragment;
    }

    public boolean teste(boolean teste){
        vdfs = teste;
        return vdfs;

    }

      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        horoscopoRepositorio = new HoroscopoRepositorio(getActivity());
        Intent intent = getActivity().getIntent();
        idd = intent.getStringExtra("Horosc");

          if(istablet()) {
              if (savedInstanceState != null) {
                  idd = getArguments().getString("Horosc");
              }
          }else {

              if (savedInstanceState != null) {
                  idd = intent.getStringExtra("Horosc");
              }
          }

        if(vdfs == true || idd != null) {
       if(vdfs == true) {
           idd = getArguments().getString("Horosc");

       }
            Cursor cursor = horoscopoRepositorio.consulta(HoroscopoContract._ID + " =" + idd);
            cursor.moveToFirst();

            if(istablet()){

                titulo = cursor.getString(cursor.getColumnIndex(HoroscopoContract.COL_SUNSIGN));
                icone = cursor.getInt(cursor.getColumnIndex(HoroscopoContract.COL_ICONE));

                ImageView img = (ImageView) view.findViewById(R.id.detail_tablet);
                TextView title_tab = (TextView) view.findViewById(R.id.title_table);
                Resources res = this.getResources();
                TypedArray logos = res.obtainTypedArray(R.array.logos);
                img.setImageDrawable(logos.getDrawable(icone));
                title_tab.setText(titulo);

            }


            msg = cursor.getString(cursor.getColumnIndex(HoroscopoContract.COL_MESSAGEM));
            data = cursor.getString(cursor.getColumnIndex(HoroscopoContract.COL_DATA));
              TextView txtData = (TextView) view.findViewById(R.id.frag_item_data);
              TextView msge = (TextView) view.findViewById(R.id.frag_item_msg);

              msge.setText(msg);
              txtData.setText(data);

            cursor.close();
        }
        return view;
    }

    private boolean istablet() {
        return getResources().getBoolean(R.bool.tablet);

    }

    private boolean isSmartphone() {
        return getResources().getBoolean(R.bool.smartphone);
    }
}
