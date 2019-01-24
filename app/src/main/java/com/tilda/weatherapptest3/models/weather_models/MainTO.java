package com.tilda.weatherapptest3.models.weather_models;

import com.google.gson.annotations.SerializedName;

public class MainTO {

    @SerializedName("temp")
    private float temperature;
    @SerializedName("pressure")
    private float pressure;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("temp_min")
    private float minTemperature;
    @SerializedName("temp_max")
    private float maxTemperature;
    @SerializedName("sea_level")
    private float seaLevel;
    @SerializedName("grnd_level")
    private float groundLevel;
    @SerializedName("temp_kf")
    private float tempKF;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(float seaLevel) {
        this.seaLevel = seaLevel;
    }

    public float getGroundLevel() {
        return groundLevel;
    }

    public void setGroundLevel(float groundLevel) {
        this.groundLevel = groundLevel;
    }

    public float getTempKF() {
        return tempKF;
    }

    public void setTempKF(float tempKF) {
        this.tempKF = tempKF;
    }
}
