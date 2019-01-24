package com.tilda.weatherapptest3.models.weather_models.current_weather;

import com.google.gson.annotations.SerializedName;

public class SysTO {

    @SerializedName("type")
    private short type;
    @SerializedName("id")
    private int id;
    @SerializedName("message")
    private float message;
    @SerializedName("country")
    private String country;
    @SerializedName("sunrise")
    private long sunrise;
    @SerializedName("sunset")
    private long sunset;
    @SerializedName("pod")
    private String pod;

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }
}
