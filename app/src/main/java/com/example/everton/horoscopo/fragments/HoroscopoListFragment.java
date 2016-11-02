package com.example.everton.horoscopo.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.everton.horoscopo.HoroscopoAdapter;
import com.example.everton.horoscopo.HoroscopoDetailActivity;
import com.example.everton.horoscopo.HoroscopoJsonbanco;
import com.example.everton.horoscopo.OnHoroscopoClickLlistener;
import com.example.everton.horoscopo.R;
import com.example.everton.horoscopo.database.HoroscopoContract;
import com.example.everton.horoscopo.database.HoroscopoRepositorio;
import com.example.everton.horoscopo.model.Horoscopo;

/**
 * Created by everton.vasconcelos on 16/09/2016.
 */
public class HoroscopoListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    HoroscopoAdapter mAdapter;
    HoroscopoRepositorio mHoroscopoRepositorio;
    ListView mlistView;
    Horoscopo horoscopo;
    OnHoroscopoClickLlistener onHoroscopoClickLlistener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHoroscopoClickLlistener) {
            onHoroscopoClickLlistener = (OnHoroscopoClickLlistener) context;
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mlistView = (ListView) view.findViewById(R.id.hosc_fragment_list);

        HoroscopoJsonbanco horoscopoJsonbanco = new HoroscopoJsonbanco(getActivity());
        mHoroscopoRepositorio = new HoroscopoRepositorio(getActivity());
        horoscopoJsonbanco.inserirHoroscopo();
       /* horoscopo = new Horoscopo();
        horoscopo.setSigno("Aries");
        horoscopo.setData("20/09/2016");
        horoscopo.setMessagem("laaaaaaaaaa");
        mHoroscopoRepositorio.inserir(horoscopo);*/


        mAdapter = new HoroscopoAdapter(getActivity(), null);
        mlistView.setAdapter(mAdapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (onHoroscopoClickLlistener != null) {
                    Cursor cursor = mAdapter.getCursor();
                    if (cursor.moveToPosition(position)) {
                        onHoroscopoClickLlistener.onMovieClick(id);
                    }
                }
            }
        });
        getLoaderManager().initLoader(0, null, this);


        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        mHoroscopoRepositorio = new HoroscopoRepositorio(getActivity());
        CursorLoader curso =  mHoroscopoRepositorio.buscar(HoroscopoContract.ALL_COLUMNS, null);

        return curso;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}