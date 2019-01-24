package com.tilda.weatherapptest3.data_access_objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tilda.weatherapptest3.models.entity.Province;

import java.util.ArrayList;
import java.util.List;

public class ProvincesDAO extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "database";
    private final static int DATABASE_VERSION = 1;

    private final static String TABLE_NAME = "provinces";

    private final static String KEY_ID = "ID";
    private final static String KEY_NAME = "name";

    public ProvincesDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addProvince(Province province) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, province.getName());

        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public Province getProvince(int id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, KEY_ID + " = " + id, null, null, null, null);
        Province province = null;
        if (cursor != null) {
            cursor.moveToFirst();
            province = new Province();
            province.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            province.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        }
        database.close();
        return province;
    }

    public List<Province> getAllProvinces() {
        List<Province> provinces = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        Province province;

        if (cursor != null) {
            cursor.moveToFirst();
            do {
                province = new Province();
                province.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                province.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                provinces.add(province);
            }
            while (cursor.moveToNext());
        }
        database.close();
        return provinces;
    }

    public int getProvincesCount() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        int count = cursor.getCount();
        database.close();
        return count;
    }

}
