package com.tilda.weatherapptest3.models.weather_models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.tilda.weatherapptest3.models.weather_models.forecast.CityTO;
import com.tilda.weatherapptest3.models.weather_models.forecast.DateTO;

import java.util.List;

public class Forecast {

    private List<DateTO> dateTOList;
    private CityTO cityTO;

    @SerializedName("cod")
    private String code;
    @SerializedName("message")
    private float message;
    @SerializedName("cnt")
    private int count;
    @SerializedName("list")
    private JsonArray listJsonArray;
    @SerializedName("city")
    private JsonObject cityJsonObject;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public JsonArray getListJsonArray() {
        return listJsonArray;
    }

    public void setListJsonArray(JsonArray listJsonArray) {
        this.listJsonArray = listJsonArray;
    }

    public JsonObject getCityJsonObject() {
        return cityJsonObject;
    }

    public void setCityJsonObject(JsonObject cityJsonObject) {
        this.cityJsonObject = cityJsonObject;
    }

    public List<DateTO> getDateTOList() {
        return dateTOList;
    }

    public void setDateTOList(List<DateTO> dateTOList) {
        this.dateTOList = dateTOList;
    }

    public CityTO getCityTO() {
        return cityTO;
    }

    public void setCityTO(CityTO cityTO) {
        this.cityTO = cityTO;
    }
}
