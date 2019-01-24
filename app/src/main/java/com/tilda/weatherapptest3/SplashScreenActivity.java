package com.tilda.weatherapptest3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class SplashScreenActivity extends AppCompatActivity {

    public static final String CITY_NAME_FA = "city_name_fa";
    public static final String CITY_NAME_EN = "city_name_en";
    public static final String UNITS = "units";
    public static final String PREFERENCES = "my_preferences";
// hello

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final SharedPreferences setting = getSharedPreferences(PREFERENCES, MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (setting.contains(CITY_NAME_FA) && setting.contains(CITY_NAME_EN)) {
                    Intent intent = new Intent(getApplicationContext(), GetWeatherDataActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), CityChooserActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2000);


    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
