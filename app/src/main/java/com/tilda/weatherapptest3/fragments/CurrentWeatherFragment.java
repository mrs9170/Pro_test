package com.tilda.weatherapptest3.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tilda.weatherapptest3.GetWeatherDataActivity;
import com.tilda.weatherapptest3.JalaliCalendar;
import com.tilda.weatherapptest3.R;
import com.tilda.weatherapptest3.data_access_objects.CitiesDAO;
import com.tilda.weatherapptest3.models.entity.City;
import com.tilda.weatherapptest3.models.weather_models.CloudsTO;
import com.tilda.weatherapptest3.models.weather_models.CoordinateTO;
import com.tilda.weatherapptest3.models.weather_models.CurrentWeatherData;
import com.tilda.weatherapptest3.models.weather_models.MainTO;
import com.tilda.weatherapptest3.models.weather_models.WeatherTO;
import com.tilda.weatherapptest3.models.weather_models.WindTO;
import com.tilda.weatherapptest3.models.weather_models.current_weather.SysTO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeatherFragment extends Fragment {

    private ImageView imageViewWeatherIcon;

    private TextView textViewTemperature, textViewWeatherDescription,
            textViewDate, textViewHumidity, textViewPressure,
            textViewCloudsDens, textViewWindSpeed, textViewWindDirection, textViewCityName;

    private String current_weather_url;

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String APP_ID = "0c030032e4000d122b4cb88842d715cd";
        SharedPreferences setting = getActivity().getSharedPreferences(GetWeatherDataActivity.PREFERENCES, Context.MODE_PRIVATE);
        String cityName = setting.getString(GetWeatherDataActivity.CITY_NAME_EN, "Shiraz");
        String units = setting.getString(GetWeatherDataActivity.UNITS, "metric");
        current_weather_url = "http://api.openweathermap.org/data/2.5/weather?"
                + "q=" + cityName
                + ",ir"
                + "&units=" + units
                + "&appid=" + APP_ID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);

        imageViewWeatherIcon = view.findViewById(R.id.imageView_weather_icon);

        textViewTemperature = view.findViewById(R.id.textView_temperature);
        textViewWeatherDescription = view.findViewById(R.id.textView_weather_description);
        textViewDate = view.findViewById(R.id.textView_date);
        textViewHumidity = view.findViewById(R.id.textView_humidity);
        textViewPressure = view.findViewById(R.id.textView_pressure);
        textViewCloudsDens = view.findViewById(R.id.textView_clouds_dens);
        textViewWindSpeed = view.findViewById(R.id.textView_wind_speed);
        textViewWindDirection = view.findViewById(R.id.textView_wind_direction);
        textViewCityName = view.findViewById(R.id.textView_city_name);

        new GetCurrentWeather().execute(current_weather_url);

        return view;
    }


    private class GetCurrentWeather extends AsyncTask<String, Void, CurrentWeatherData> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected CurrentWeatherData doInBackground(String... urls) {

            CurrentWeatherData currentWeatherData = null;
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
                currentWeatherData = gson.fromJson(response.toString(), CurrentWeatherData.class);
                currentWeatherData.setCoordinateTO(gson.fromJson(currentWeatherData.getCoordinateJsonObject(), CoordinateTO.class));
                currentWeatherData.setMainTO(gson.fromJson(currentWeatherData.getMainJsonObject(), MainTO.class));
                currentWeatherData.setWindTO(gson.fromJson(currentWeatherData.getWindJsonObject(), WindTO.class));
                currentWeatherData.setCloudsTO(gson.fromJson(currentWeatherData.getCloudsJsonObject(), CloudsTO.class));
                currentWeatherData.setSysTO(gson.fromJson(currentWeatherData.getSysJsonObject(), SysTO.class));
                currentWeatherData.setWeatherTO(gson.fromJson(currentWeatherData.getWeatherJsonArray().get(0), WeatherTO.class));
                return currentWeatherData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(CurrentWeatherData currentWeatherData) {
            super.onPostExecute(currentWeatherData);

            JalaliCalendar jalaliCalendar = new JalaliCalendar();

            CitiesDAO citiesDAO = new CitiesDAO(getActivity());
            City tempCity = citiesDAO.getCityEn(currentWeatherData.getName());
            textViewCityName.setText(tempCity.getName());
            textViewTemperature.setText(String.valueOf(((int) currentWeatherData.getMainTO().getTemperature())));
            textViewHumidity.setText(String.valueOf(currentWeatherData.getMainTO().getHumidity()));
            textViewPressure.setText(String.valueOf(currentWeatherData.getMainTO().getPressure()));
            textViewCloudsDens.setText(String.valueOf(currentWeatherData.getCloudsTO().getAll()));
            textViewWindSpeed.setText(String.valueOf(currentWeatherData.getWindTO().getSpeed()));
            textViewWindDirection.setText(String.valueOf(currentWeatherData.getWindTO().getDegree()));
            textViewWeatherDescription.setText(currentWeatherData.getWeatherTO().getDescription());
            textViewDate.setText(jalaliCalendar.getDayOfWeekDayMonthString());

            new ImageDownloader().execute("https://openweathermap.org/img/w/" + currentWeatherData.getWeatherTO().getIcon() + ".png");

        }
    }

    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            InputStream input;

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                input = connection.getInputStream();

                Bitmap result = BitmapFactory.decodeStream(input);


                return result;


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageViewWeatherIcon.setImageBitmap(bitmap);
        }
    }
}
