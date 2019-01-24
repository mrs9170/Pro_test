package com.tilda.weatherapptest3.models.weather_models;

import com.google.gson.annotations.SerializedName;

public class CoordinateTO {

    @SerializedName("lon")
    private double longitude;
    @SerializedName("lat")
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
