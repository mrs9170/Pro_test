package com.tilda.weatherapptest3.models.weather_models.forecast;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.tilda.weatherapptest3.models.weather_models.CoordinateTO;

public class CityTO {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("coord")
    private JsonObject coordinateJsonObject;
    @SerializedName("country")
    private String country;
    @SerializedName("population")
    private long population;

    private CoordinateTO coordinateTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonObject getCoordinateJsonObject() {
        return coordinateJsonObject;
    }

    public void setCoordinateJsonObject(JsonObject coordinateJsonObject) {
        this.coordinateJsonObject = coordinateJsonObject;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public CoordinateTO getCoordinateTO() {
        return coordinateTO;
    }

    public void setCoordinateTO(CoordinateTO coordinateTO) {
        this.coordinateTO = coordinateTO;
    }
}
