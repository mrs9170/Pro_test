package com.tilda.weatherapptest3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.LocationRequest;
import com.tilda.weatherapptest3.adapters.ViewPagerAdapter;
import com.tilda.weatherapptest3.fragments.CurrentWeatherFragment;
import com.tilda.weatherapptest3.fragments.ForecastFragment;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class GetWeatherDataActivity extends AppCompatActivity {

    public static final String CITY_NAME_FA = "city_name_fa";
    public static final String CITY_NAME_EN = "city_name_en";
    public static final String UNITS = "units";
    public static final String PREFERENCES = "my_preferences";

    private LocationRequest mLocationRequest;

    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_weather_data);

        SharedPreferences setting = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        Button btnChoosePlace = findViewById(R.id.btn_choose_place);

        ViewPager viewPager = findViewById(R.id.view_pager);
        ForecastFragment forecastFragment = new ForecastFragment();
        CurrentWeatherFragment currentWeatherFragment = new CurrentWeatherFragment();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(currentWeatherFragment, "وضعیت هوای فعلی");
        viewPagerAdapter.addFragment(forecastFragment, "پیش بینی وضعیت هوا");
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        btnChoosePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CityChooserActivity.class);
                startActivity(intent);
            }
        });

        setting.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                ViewPager viewPager = findViewById(R.id.view_pager);
                ForecastFragment forecastFragment = new ForecastFragment();
                CurrentWeatherFragment currentWeatherFragment = new CurrentWeatherFragment();
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
                viewPagerAdapter.addFragment(currentWeatherFragment, "وضعیت هوای فعلی");
                viewPagerAdapter.addFragment(forecastFragment, "پیش بینی وضعیت هوا");
                viewPager.setAdapter(viewPagerAdapter);
                TabLayout tabLayout = findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("خروج");
        builder.setMessage("آیا مطمئنید ؟");

        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
