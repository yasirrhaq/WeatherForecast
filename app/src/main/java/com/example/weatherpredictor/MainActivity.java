package com.example.weatherpredictor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvLocationName;

    private ImageView ivWeatherIcon;

    private TextView tvWeatherCode;
    private TextView tvTemperature;
    private TextView tvWindSpeed;
    private TextView tvWindDirection;
    private RecyclerView rvWeeklyForecast;
    private WeeklyWeatherAdapter weeklyWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the TextViews and RecyclerView
        tvLocationName = findViewById(R.id.tvLocationName);
        ivWeatherIcon = findViewById(R.id.ivWeatherIcon);
        tvWeatherCode = findViewById(R.id.tvWeatherCode);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        rvWeeklyForecast = findViewById(R.id.rvWeeklyForecast);

        // Create the adapter for the RecyclerView
        weeklyWeatherAdapter = new WeeklyWeatherAdapter();

        // Set Layout Manager
        rvWeeklyForecast.setLayoutManager(new LinearLayoutManager(this));

        // Set the adapter to the RecyclerView
        rvWeeklyForecast.setAdapter(weeklyWeatherAdapter);

        // Execute the network request using AsyncTask
        new NetworkTask().execute();
    }

    private class NetworkTask extends AsyncTask<Void, Void, WeatherData> {
        @Override
        protected WeatherData doInBackground(Void... voids) {
            // Perform the network request here
            String jsonResponse = WeatherApiClient.getWeatherData();
            Log.d("WeatherData", "JSON Response: " + jsonResponse);

            // Parse the JSON response and return WeatherData object
            WeatherData weatherData = WeatherDataParser.parseWeatherData(jsonResponse);

            return weatherData;
        }

        @Override
        protected void onPostExecute(WeatherData weatherData) {
            if (weatherData != null) {
                // Update the UI with the parsed weather data
                double latitude = weatherData.getLatitude();
                double longitude = weatherData.getLongitude();

                // Convert latitude and longitude to location name
                String locationName = GeocodingUtils.getCityFromCoordinates(getApplicationContext(),latitude, longitude);
                tvLocationName.setText(locationName);
                ivWeatherIcon.setImageResource(WeatherUtils.getWeatherIcon(weatherData.getCondition()));
                tvWeatherCode.setText(WeatherUtils.getWeatherText(weatherData.getCondition()));
                tvTemperature.setText(weatherData.getTemperature()+"°C");
                tvWindSpeed.setText(String.valueOf(weatherData.getWindSpeed()));

                // Update the weekly weather forecast
                weeklyWeatherAdapter.setWeeklyWeather(weatherData.getWeeklyWeather());
                weeklyWeatherAdapter.notifyDataSetChanged(); // Notify the adapter about the data change
            }
        }
    }
}
