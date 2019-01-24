package com.tilda.weatherapptest3.models.weather_models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.tilda.weatherapptest3.models.weather_models.current_weather.SysTO;

public class CurrentWeatherData {

    @SerializedName("coord")
    private JsonObject coordinateJsonObject;
    @SerializedName("weather")
    private JsonArray weatherJsonArray;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private JsonObject mainJsonObject;
    @SerializedName("visibility")
    private int visibility;
    @SerializedName("wind")
    private JsonObject windJsonObject;
    @SerializedName("clouds")
    private JsonObject cloudsJsonObject;
    @SerializedName("dt")
    private long dt;
    @SerializedName("sys")
    private JsonObject sysJsonObject;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private int code;

    private CoordinateTO coordinateTO;
    private WeatherTO weatherTO;
    private MainTO mainTO;
    private WindTO windTO;
    private CloudsTO cloudsTO;
    private SysTO sysTO;

    public JsonObject getCoordinateJsonObject() {
        return coordinateJsonObject;
    }

    public void setCoordinateJsonObject(JsonObject coordinateJsonObject) {
        this.coordinateJsonObject = coordinateJsonObject;
    }

    public JsonArray getWeatherJsonArray() {
        return weatherJsonArray;
    }

    public void setWeatherJsonArray(JsonArray weatherJsonArray) {
        this.weatherJsonArray = weatherJsonArray;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public JsonObject getMainJsonObject() {
        return mainJsonObject;
    }

    public void setMainJsonObject(JsonObject mainJsonObject) {
        this.mainJsonObject = mainJsonObject;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public JsonObject getWindJsonObject() {
        return windJsonObject;
    }

    public void setWindJsonObject(JsonObject windJsonObject) {
        this.windJsonObject = windJsonObject;
    }

    public JsonObject getCloudsJsonObject() {
        return cloudsJsonObject;
    }

    public void setCloudsJsonObject(JsonObject cloudsJsonObject) {
        this.cloudsJsonObject = cloudsJsonObject;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public JsonObject getSysJsonObject() {
        return sysJsonObject;
    }

    public void setSysJsonObject(JsonObject sysJsonObject) {
        this.sysJsonObject = sysJsonObject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        // باید در این قسمت با دسترسی به فایل Strings معادل فارسی نام لاتین را پیدا کرده و لحاظ کنیم
        /*String name = this.name;
        switch (this.name) {
            case "Shiraz":
                name = "شیراز";
                break;
            case "Tehran":
                name = "تهران";
                break;
        }*/
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CoordinateTO getCoordinateTO() {
        return coordinateTO;
    }

    public void setCoordinateTO(CoordinateTO coordinateTO) {
        this.coordinateTO = coordinateTO;
    }

    public WeatherTO getWeatherTO() {
        return weatherTO;
    }

    public void setWeatherTO(WeatherTO weatherTO) {
        this.weatherTO = weatherTO;
    }

    public MainTO getMainTO() {
        return mainTO;
    }

    public void setMainTO(MainTO mainTO) {
        this.mainTO = mainTO;
    }

    public WindTO getWindTO() {
        return windTO;
    }

    public void setWindTO(WindTO windTO) {
        this.windTO = windTO;
    }

    public CloudsTO getCloudsTO() {
        return cloudsTO;
    }

    public void setCloudsTO(CloudsTO cloudsTO) {
        this.cloudsTO = cloudsTO;
    }

    public SysTO getSysTO() {
        return sysTO;
    }

    public void setSysTO(SysTO sysTO) {
        this.sysTO = sysTO;
    }
}
