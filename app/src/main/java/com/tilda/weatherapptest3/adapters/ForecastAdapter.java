package com.tilda.weatherapptest3.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tilda.weatherapptest3.R;
import com.tilda.weatherapptest3.models.weather_models.forecast.DateTO;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private List<DateTO> dates;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewWeatherIcon;
        TextView textViewTemperature, textViewWeatherDescription,
                textViewDate, textViewHumidity, textViewPressure, textViewDayOfWeek,
                textViewCloudsDens, textViewWindSpeed, textViewWindDirection, textViewTime;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewWeatherIcon = itemView.findViewById(R.id.imageView_weather_icon);

            textViewTemperature = itemView.findViewById(R.id.textView_temperature);
            textViewWeatherDescription = itemView.findViewById(R.id.textView_weather_description);
            textViewDate = itemView.findViewById(R.id.textView_date);
            textViewHumidity = itemView.findViewById(R.id.textView_humidity);
            textViewPressure = itemView.findViewById(R.id.textView_pressure);
            textViewCloudsDens = itemView.findViewById(R.id.textView_clouds_dens);
            textViewWindSpeed = itemView.findViewById(R.id.textView_wind_speed);
            textViewWindDirection = itemView.findViewById(R.id.textView_wind_direction);
            textViewDayOfWeek = itemView.findViewById(R.id.textView_dayOfWeek);
            textViewTime = itemView.findViewById(R.id.textView_time);
        }
    }

    public ForecastAdapter(ArrayList<DateTO> dates, Context context) {
        this.dates = dates;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.recycler_view_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        DateTO date = dates.get(position);

        viewHolder.textViewTemperature.setText(String.valueOf(((int) date.getMainTO().getTemperature())));
        viewHolder.textViewHumidity.setText(String.valueOf(date.getMainTO().getHumidity()));
        viewHolder.textViewPressure.setText(String.valueOf(date.getMainTO().getPressure()));
        viewHolder.textViewCloudsDens.setText(String.valueOf(date.getCloudsTO().getAll()));
        viewHolder.textViewWindSpeed.setText(String.valueOf(date.getWindTO().getSpeed()));
        viewHolder.textViewWindDirection.setText(String.valueOf(date.getWindTO().getDegree()));
        viewHolder.textViewWeatherDescription.setText(String.valueOf(date.getWeatherTO().getDescription()));
        viewHolder.textViewDate.setText(date.getDate());
        viewHolder.textViewDayOfWeek.setText(date.getDayOfWeek());
        viewHolder.textViewTime.setText(date.getTime());

        ImageDownloader imageDownloader = new ImageDownloader(viewHolder);
        imageDownloader.execute("https://openweathermap.org/img/w/" + date.getWeatherTO().getIcon() + ".png");
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }


    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        private ViewHolder viewHolder;

        ImageDownloader(ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

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
            viewHolder.imageViewWeatherIcon.setImageBitmap(bitmap);
        }
    }
}
