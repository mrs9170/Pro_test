package com.tilda.weatherapptest3;

import android.app.Application;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class AppController extends Application {

    private static AppController instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/b_yekan.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    public static synchronized AppController getInstance() {
        return instance;
    }

}
