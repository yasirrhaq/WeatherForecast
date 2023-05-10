package com.example.weatherpredictor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiClient {
    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast";

    public static String getWeatherData() {
        String weatherUrl = BASE_URL + "?latitude=-7.98&longitude=112.63&daily=weathercode&current_weather=true&timezone=auto";

        try {
            URL url = new URL(weatherUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
