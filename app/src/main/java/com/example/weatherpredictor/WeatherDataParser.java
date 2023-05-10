package com.example.weatherpredictor;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class WeatherDataParser {
    public static WeatherData parseWeatherData(String response) {
        try {
            JSONObject jsonWeather = new JSONObject(response);

            // Get the current weather information
            JSONObject currentWeather = jsonWeather.getJSONObject("current_weather");
            double temperature = currentWeather.getDouble("temperature");
            double windSpeed = currentWeather.getDouble("windspeed");
            int weatherCode = currentWeather.getInt("weathercode");

            // Get the geo location information
            double latitude = jsonWeather.getDouble("latitude");
            double longitude = jsonWeather.getDouble("longitude");

            // Get the daily weather forecast
            JSONObject dailyWeather = jsonWeather.getJSONObject("daily");
            JSONArray timeArray = dailyWeather.getJSONArray("time");
            JSONArray weatherCodeArray = dailyWeather.getJSONArray("weathercode");

            ArrayList<DailyWeather> weeklyWeather = new ArrayList<>();
            for (int i = 0; i < timeArray.length(); i++) {
                String date = timeArray.getString(i);
                int weatherCodeDaily = weatherCodeArray.getInt(i);

                int weatherIcon = WeatherUtils.getWeatherIcon(weatherCodeDaily);
                DailyWeather dailyWeatherItem = new DailyWeather(date, weatherCodeDaily, weatherIcon);
                weeklyWeather.add(dailyWeatherItem);
            }

            WeatherData weatherData = new WeatherData(weatherCode, temperature, windSpeed, latitude, longitude, weeklyWeather);
            return weatherData;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}