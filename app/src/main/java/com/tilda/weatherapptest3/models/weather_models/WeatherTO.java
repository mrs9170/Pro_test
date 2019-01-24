package com.tilda.weatherapptest3.models.weather_models;

import com.google.gson.annotations.SerializedName;

public class WeatherTO {

    @SerializedName("id")
    private int id;
    @SerializedName("main")
    private String main;
    @SerializedName("description")
    private String description;
    @SerializedName("icon")
    private String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {

        String description = this.description;

        switch (this.description) {
            case "clear sky":
                description = "صاف";
                break;
            case "few clouds":
                description = "نیمه ابری";
                break;
            case "light rain":
                description = "بارش کم";
                break;
            case "scattered clouds":
                description = "ابرهای پراکنده";
                break;
            case "moderate rain":
                description = "باران ملایم";
                break;
            case "overcast clouds":
                description = "ابری";
                break;
            case "broken clouds":
                description = "ابرهای پراکنده";
                break;
            case "haze":
                description = "مه آلود";
                break;
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
