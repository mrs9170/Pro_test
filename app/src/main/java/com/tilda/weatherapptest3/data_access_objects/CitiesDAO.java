package com.tilda.weatherapptest3.data_access_objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tilda.weatherapptest3.models.entity.City;

import java.util.LinkedList;
import java.util.List;


public class CitiesDAO extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "database";
    private final static int DATABASE_VERSION = 1;

    private final static String TABLE_NAME = "cities";

    private final static String KEY_ID = "ID";
    private final static String KEY_NAME = "name";
    private final static String KEY_NAME_EN = "name_en";
    private final static String KEY_PROVINCE_ID = "province_ID";
    private final static String KEY_IS_FAVORITE = "is_favorite";


    public CitiesDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCity(City city) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, city.getName());
        values.put(KEY_NAME_EN, city.getNameEN());
        values.put(KEY_PROVINCE_ID, city.getProvince_id());
        values.put(KEY_IS_FAVORITE, city.getIsFavorite());
        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public City getCity(int id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, KEY_ID + " = " + id, null, null, null, null);
        City city = null;
        if (cursor != null) {
            cursor.moveToFirst();
            city = new City();
            city.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            city.setNameEN(cursor.getString(cursor.getColumnIndex(KEY_NAME_EN)));
            city.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            city.setProvince_id(cursor.getInt(cursor.getColumnIndex(KEY_PROVINCE_ID)));
            city.setIsFavorite(cursor.getInt(cursor.getColumnIndex(KEY_IS_FAVORITE)));
        }
        database.close();
        return city;
    }

    public City getCity(String name) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, KEY_NAME + " = '" + name + "'", null, null, null, null);
        City city = null;
        if (cursor != null) {
            cursor.moveToFirst();
            city = new City();
            city.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            city.setNameEN(cursor.getString(cursor.getColumnIndex(KEY_NAME_EN)));
            city.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            city.setProvince_id(cursor.getInt(cursor.getColumnIndex(KEY_PROVINCE_ID)));
            city.setIsFavorite(cursor.getInt(cursor.getColumnIndex(KEY_IS_FAVORITE)));
        }
        database.close();
        return city;
    }


    public City getCityEn(String nameEn) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, KEY_NAME_EN + " = '" + nameEn + "'", null, null, null, null);
        City city = null;
        if (cursor != null) {
            cursor.moveToFirst();
            city = new City();
            city.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            city.setNameEN(cursor.getString(cursor.getColumnIndex(KEY_NAME_EN)));
            city.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            city.setProvince_id(cursor.getInt(cursor.getColumnIndex(KEY_PROVINCE_ID)));
            city.setIsFavorite(cursor.getInt(cursor.getColumnIndex(KEY_IS_FAVORITE)));
        }
        database.close();
        return city;
    }



    public List<City> getAllCities(int provinceId) {
        List<City> cities = new LinkedList<>();
        SQLiteDatabase database = getReadableDatabase();
        String condition = KEY_PROVINCE_ID + " = " + provinceId;
        try {
            Cursor cursor = database.query(TABLE_NAME, null, condition, null, null, null, null);
            City city;

            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    city = new City();
                    city.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    city.setNameEN(cursor.getString(cursor.getColumnIndex(KEY_NAME_EN)));
                    city.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                    city.setProvince_id(cursor.getInt(cursor.getColumnIndex(KEY_PROVINCE_ID)));
                    city.setIsFavorite(cursor.getInt(cursor.getColumnIndex(KEY_IS_FAVORITE)));
                    cities.add(city);
                }
                while (cursor.moveToNext());
            }
            database.close();

        } catch (CursorIndexOutOfBoundsException e) {
            e.printStackTrace();
            cities = null;
        }
        return cities;
    }

    public List<City> getAllFavoriteCities(int isFavorite) {
        List<City> cities = new LinkedList<>();
        SQLiteDatabase database = getReadableDatabase();
        String condition = KEY_IS_FAVORITE + " = " + isFavorite;
        try {
            Cursor cursor = database.query(TABLE_NAME, null, condition, null, null, null, null);
            City city;

            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    city = new City();
                    city.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    city.setNameEN(cursor.getString(cursor.getColumnIndex(KEY_NAME_EN)));
                    city.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                    city.setProvince_id(cursor.getInt(cursor.getColumnIndex(KEY_PROVINCE_ID)));
                    city.setIsFavorite(cursor.getInt(cursor.getColumnIndex(KEY_IS_FAVORITE)));
                    cities.add(city);
                }
                while (cursor.moveToNext());
            }
            database.close();

        } catch (CursorIndexOutOfBoundsException e) {
            e.printStackTrace();
            cities = null;
        }
        return cities;
    }

    public int getCitiesCount(int provinceId) {
        SQLiteDatabase database = getReadableDatabase();
        String condition = KEY_PROVINCE_ID + " = " + provinceId;
        Cursor cursor = database.query(TABLE_NAME, null, condition, null, null, null, null);
        int count = cursor.getCount();
        database.close();
        return count;
    }




}

