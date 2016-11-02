package com.example.everton.horoscopo.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class HoroscopoProvider extends ContentProvider {

    private static final String PATH = "horoscopo";
    private static final int TYPE_HOROSCOPO_LIST = 0;
    private static final int TYPE_HOROSCOPO_BY_ID = 1;
    private static final String AUTHORITY = "everton.android.horoscopo";

    public static Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static Uri HOROSCOPO_URI = BASE_URI.withAppendedPath(BASE_URI, PATH);


    private UriMatcher mMatcher;
    private HoroscopoDBHelper mhoroscopoHelper;


    public HoroscopoProvider() {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTHORITY, PATH, TYPE_HOROSCOPO_LIST);
        mMatcher.addURI(AUTHORITY, PATH + "/#", TYPE_HOROSCOPO_BY_ID);
    }

    @Override
    public boolean onCreate() {
        mhoroscopoHelper = new HoroscopoDBHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {

        int uriType = mMatcher.match(uri);
        switch (uriType) {
            case TYPE_HOROSCOPO_LIST:

                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY;
            case TYPE_HOROSCOPO_BY_ID:
                return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY;
            default:
                throw new IllegalArgumentException("Invalid Uri");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        try {

            int uriType = mMatcher.match(uri);
            if (uriType == TYPE_HOROSCOPO_LIST) {
                SQLiteDatabase db = mhoroscopoHelper.getWritableDatabase();
                long id = db.insert(HoroscopoContract.TABLE_NAME, null, values);
                db.close();
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(HOROSCOPO_URI, id);
            } else {
                throw new IllegalArgumentException("Invalid Uri");
            }
        } catch (Exception e) {
            Log.e("Erro ao inserir:", e.getMessage());
            return ContentUris.withAppendedId(HOROSCOPO_URI, 0);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = mMatcher.match(uri);
        if (uriType == TYPE_HOROSCOPO_BY_ID) {
            SQLiteDatabase db = mhoroscopoHelper.getWritableDatabase();
            long id = ContentUris.parseId(uri);
            int rowsAffected = db.delete(
                    HoroscopoContract.TABLE_NAME,
                    HoroscopoContract._ID + " = ?",
                    new String[]{String.valueOf(id)});
            getContext().getContentResolver().notifyChange(uri, null);
            db.close();

            return rowsAffected;

        } else {
            throw new IllegalArgumentException("Invalid Uri");
        }
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int uriType = mMatcher.match(uri);
        SQLiteDatabase db = mhoroscopoHelper.getReadableDatabase();
        Cursor cursor;
        switch (uriType) {
            case TYPE_HOROSCOPO_LIST:
                cursor = db.query(HoroscopoContract.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case TYPE_HOROSCOPO_BY_ID:
                long id = ContentUris.parseId(uri);
                cursor = db.query(HoroscopoContract.TABLE_NAME,
                        projection, HoroscopoContract._ID + " = ?",
                        new String[]{String.valueOf(id)}, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Invalid Uri");
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        int uriType = mMatcher.match(uri);
        if (uriType == TYPE_HOROSCOPO_LIST) {

            SQLiteDatabase db = mhoroscopoHelper.getWritableDatabase();
            int rowsAffected = db.update(HoroscopoContract.TABLE_NAME, values, selection, selectionArgs);
            db.close();
            getContext().getContentResolver().notifyChange(uri, null);
            return rowsAffected;
        }else{
            throw new IllegalArgumentException("Invalid Uri");
        }
    }

    }

