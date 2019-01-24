package com.tilda.weatherapptest3.models.weather_models;

import com.google.gson.annotations.SerializedName;

public class CloudsTO {

    @SerializedName("all")
    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
