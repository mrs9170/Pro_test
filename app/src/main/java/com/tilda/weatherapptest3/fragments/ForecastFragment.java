package com.tilda.weatherapptest3.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.tilda.weatherapptest3.GetWeatherDataActivity;
import com.tilda.weatherapptest3.R;
import com.tilda.weatherapptest3.adapters.ForecastAdapter;
import com.tilda.weatherapptest3.models.weather_models.CloudsTO;
import com.tilda.weatherapptest3.models.weather_models.Forecast;
import com.tilda.weatherapptest3.models.weather_models.MainTO;
import com.tilda.weatherapptest3.models.weather_models.WeatherTO;
import com.tilda.weatherapptest3.models.weather_models.WindTO;
import com.tilda.weatherapptest3.models.weather_models.current_weather.SysTO;
import com.tilda.weatherapptest3.models.weather_models.forecast.CityTO;
import com.tilda.weatherapptest3.models.weather_models.forecast.DateTO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {

    private RecyclerView recyclerView;
    private String forecast_url;

    public ForecastFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences setting = getActivity().getSharedPreferences(GetWeatherDataActivity.PREFERENCES, Context.MODE_PRIVATE);
        String cityName = setting.getString(GetWeatherDataActivity.CITY_NAME_EN, "Shiraz");
        String units = setting.getString(GetWeatherDataActivity.UNITS, "metric");
        String APP_ID = "0c030032e4000d122b4cb88842d715cd";
        forecast_url = "http://api.openweathermap.org/data/2.5/forecast?"
                + "q=" + cityName
                + ",ir"
                + "&units=" + units
                + "&appid=" + APP_ID;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        recyclerView = view.findViewById(R.id.my_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        new GetForecastData().execute(forecast_url);

        return view;
    }


    @SuppressLint("StaticFieldLeak")
    private class GetForecastData extends AsyncTask<String, Void, Forecast> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Forecast doInBackground(String... urls) {

            Forecast forecast = null;
            InputStream inputStream;
            StringBuilder response = new StringBuilder();

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null)
                        response.append(line);
                }

                Gson gson = new Gson();
                forecast = gson.fromJson(response.toString(), Forecast.class);
                forecast.setCityTO(gson.fromJson(forecast.getCityJsonObject(), CityTO.class));
                List<DateTO> dates = new ArrayList<>();
                for (int i = 0; i < forecast.getCount(); i++) {
                    DateTO date = gson.fromJson(forecast.getListJsonArray().get(i), DateTO.class);
                    date.setMainTO(gson.fromJson(date.getMainJsonObject(), MainTO.class));
                    date.setWeatherTO(gson.fromJson(date.getWeatherJsonArray().get(0), WeatherTO.class));
                    date.setCloudsTO(gson.fromJson(date.getCloudsJsonObject(), CloudsTO.class));
                    date.setWindTO(gson.fromJson(date.getWindJsonObject(), WindTO.class));
                    date.setSysTO(gson.fromJson(date.getSysJsonObject(), SysTO.class));
                    dates.add(date);
                }
                forecast.setDateTOList(dates);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return forecast;
        }

        @Override
        protected void onPostExecute(Forecast forecast) {
            super.onPostExecute(forecast);

            ForecastAdapter forecastAdapter = new ForecastAdapter((ArrayList<DateTO>) forecast.getDateTOList(), getContext());
            recyclerView.setAdapter(forecastAdapter);
        }
    }

    public void getDataFromInternet() {
        new GetForecastData().execute(this.forecast_url);
    }
}
