package com.tilda.weatherapptest3.models.weather_models.forecast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.tilda.weatherapptest3.JalaliCalendar;
import com.tilda.weatherapptest3.models.weather_models.CloudsTO;
import com.tilda.weatherapptest3.models.weather_models.MainTO;
import com.tilda.weatherapptest3.models.weather_models.WeatherTO;
import com.tilda.weatherapptest3.models.weather_models.WindTO;
import com.tilda.weatherapptest3.models.weather_models.current_weather.SysTO;

import java.util.GregorianCalendar;

public class DateTO {

    @SerializedName("dt")
    private long requestTime;
    @SerializedName("main")
    private JsonObject mainJsonObject;
    @SerializedName("weather")
    private JsonArray weatherJsonArray;
    @SerializedName("clouds")
    private JsonObject cloudsJsonObject;
    @SerializedName("wind")
    private JsonObject windJsonObject;
    @SerializedName("sys")
    private JsonObject sysJsonObject;
    @SerializedName("dt_txt")
    private String date;

    private MainTO mainTO;
    private WeatherTO weatherTO;
    private CloudsTO cloudsTO;
    private WindTO windTO;
    private SysTO sysTO;

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public JsonObject getMainJsonObject() {
        return mainJsonObject;
    }

    public void setMainJsonObject(JsonObject mainJsonObject) {
        this.mainJsonObject = mainJsonObject;
    }

    public JsonArray getWeatherJsonArray() {
        return weatherJsonArray;
    }

    public void setWeatherJsonArray(JsonArray weatherJsonArray) {
        this.weatherJsonArray = weatherJsonArray;
    }

    public JsonObject getCloudsJsonObject() {
        return cloudsJsonObject;
    }

    public void setCloudsJsonObject(JsonObject cloudsJsonObject) {
        this.cloudsJsonObject = cloudsJsonObject;
    }

    public JsonObject getWindJsonObject() {
        return windJsonObject;
    }

    public void setWindJsonObject(JsonObject windJsonObject) {
        this.windJsonObject = windJsonObject;
    }

    public JsonObject getSysJsonObject() {
        return sysJsonObject;
    }

    public void setSysJsonObject(JsonObject sysJsonObject) {
        this.sysJsonObject = sysJsonObject;
    }

    public String getDate() {

        int year, month, day;
        year = Integer.parseInt(this.date.substring(0, 4));
        month = Integer.parseInt(this.date.substring(5, 7));
        day = Integer.parseInt(this.date.substring(8, 10));
        JalaliCalendar jalaliCalendar = new JalaliCalendar(new GregorianCalendar(year, month - 1, day));
        return jalaliCalendar.toString();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        int year, month, day;
        year = Integer.parseInt(this.date.substring(0, 4));
        month = Integer.parseInt(this.date.substring(5, 7));
        day = Integer.parseInt(this.date.substring(8, 10));
        JalaliCalendar jalaliCalendar = new JalaliCalendar(new GregorianCalendar(year, month - 1, day));
        return jalaliCalendar.getDayOfWeekString();
    }

    public String getTime() {
        return this.date.substring(11, 16);
    }

    public MainTO getMainTO() {
        return mainTO;
    }

    public void setMainTO(MainTO mainTO) {
        this.mainTO = mainTO;
    }

    public WeatherTO getWeatherTO() {
        return weatherTO;
    }

    public void setWeatherTO(WeatherTO weatherTO) {
        this.weatherTO = weatherTO;
    }

    public CloudsTO getCloudsTO() {
        return cloudsTO;
    }

    public void setCloudsTO(CloudsTO cloudsTO) {
        this.cloudsTO = cloudsTO;
    }

    public WindTO getWindTO() {
        return windTO;
    }

    public void setWindTO(WindTO windTO) {
        this.windTO = windTO;
    }

    public SysTO getSysTO() {
        return sysTO;
    }

    public void setSysTO(SysTO sysTO) {
        this.sysTO = sysTO;
    }
}
