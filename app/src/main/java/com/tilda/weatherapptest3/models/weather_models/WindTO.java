package com.tilda.weatherapptest3.models.weather_models;

import com.google.gson.annotations.SerializedName;

public class WindTO {

    @SerializedName("speed")
    private float speed;
    @SerializedName("deg")
    private float degree;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }
}
