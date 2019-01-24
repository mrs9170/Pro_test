package com.tilda.weatherapptest3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.tilda.weatherapptest3.adapters.PlacesExpListAdapter;
import com.tilda.weatherapptest3.data_access_objects.CitiesDAO;
import com.tilda.weatherapptest3.data_access_objects.ProvincesDAO;
import com.tilda.weatherapptest3.models.entity.City;
import com.tilda.weatherapptest3.models.entity.Province;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class CityChooserActivity extends AppCompatActivity {

    private SharedPreferences setting;
    private SharedPreferences.Editor editor;

    private TextView selectedCityTextView;

    private List<Province> provinces;
    private Map<Province, List<City>> cities;
    private ProvincesDAO provincesDAO;
    private CitiesDAO citiesDAO;

    private City selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_chooser);

        provinces = new ArrayList<>();
        cities = new LinkedHashMap<>();
        provincesDAO = new ProvincesDAO(this);
        citiesDAO = new CitiesDAO(this);

        fetchData();

        setting = getSharedPreferences(GetWeatherDataActivity.PREFERENCES, Context.MODE_PRIVATE);


        ExpandableListView chooseCityExpList = findViewById(R.id.chooseCity_expList);
        Button chooseBtn = findViewById(R.id.chooseBtn_chooseCity);

        PlacesExpListAdapter listAdapter = new PlacesExpListAdapter(this);
        chooseCityExpList.setAdapter(listAdapter);
        selectedCityTextView = findViewById(R.id.selectedCity_textView);

        chooseCityExpList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedCityNameFn = ((TextView) v.findViewById(R.id.city_item_textView)).getText().toString();
                selectedCityTextView.setText(selectedCityNameFn);
                selectedCity = citiesDAO.getCity(selectedCityNameFn);
                return false;
            }
        });

        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = setting.edit();
                editor.putString(GetWeatherDataActivity.CITY_NAME_EN, selectedCity.getNameEN());
                editor.putString(GetWeatherDataActivity.CITY_NAME_FA, selectedCity.getName());
                editor.apply();

                Intent intent = new Intent(CityChooserActivity.this, GetWeatherDataActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void fetchData() {

        if (provincesDAO.getProvincesCount() != 0)
            // اگر جدول استان ها در دیتابیس خالی نباشد آنها را به لیست موجود انتساب بده
            provinces = provincesDAO.getAllProvinces();
        else
            //در غیر اینصورت لیست موجود را با صفر خانه ایجاد کن
            provinces = new ArrayList<>(0);

        for (Province province : provinces) {
            // به ازا هر یک از عناصر موجود در لیست استان ها اگر جدول شهرهای مربوط به هر استان خالی نباشد
            // یک عنصر جدید با کلید نام استان و لیست شهرهای مربوط به آن استان را در مپ وارد کن
            if (citiesDAO.getCitiesCount(province.getId()) != 0)
                cities.put(province, citiesDAO.getAllCities(province.getId()));
            else
                // و اگر لیست شهرهای مربوط به هر یک از استان ها خالی باشد
                // یک لیست خالی به همراه نام استان در مپ وارد کن
                cities.put(province, new ArrayList<City>(0));
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

}
