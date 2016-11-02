package com.example.everton.horoscopo;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.everton.horoscopo.database.HoroscopoContract;

/**
 * Created by Everton on 11/09/2016.
 */
public class HoroscopoAdapter extends SimpleCursorAdapter {

    private static final int LAYOUT = R.layout.content_item_lista;
    public HoroscopoAdapter(Context context, Cursor c) {
        super(context, LAYOUT, c, HoroscopoContract.ALL_COLUMNS, null, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(LAYOUT, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView imageViewIcone = (ImageView) view.findViewById(R.id.item_hosc_icone);
        TextView textViewTitle = (TextView) view.findViewById(R.id.item_hosc_signo);
        TextView textViewdata = (TextView) view.findViewById(R.id.item_hosc_data);

        String icone = cursor.getString(cursor.getColumnIndex(HoroscopoContract.COL_ICONE));
        String title = cursor.getString(cursor.getColumnIndex(HoroscopoContract.COL_SUNSIGN));
        String data = cursor.getString(cursor.getColumnIndex(HoroscopoContract.COL_DATA));

        Resources res = context.getResources();
        TypedArray logos = res.obtainTypedArray(R.array.logos);
        imageViewIcone.setImageDrawable(logos.getDrawable(Integer.parseInt(icone)));
        textViewTitle.setText(title);
        textViewdata.setText(data);
    }
}
