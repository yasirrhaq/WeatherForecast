package com.example.weatherpredictor;

import java.util.List;

public class WeatherData {
    private int condition;
    private double temperature;
    private double windSpeed;
    private double latitude;
    private double longitude;
    private List<DailyWeather> weeklyWeather;

    public WeatherData(int condition, double temperature, double windSpeed, double latitude, double longitude, List<DailyWeather> weeklyWeather) {
        this.condition = condition;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weeklyWeather = weeklyWeather;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<DailyWeather> getWeeklyWeather() {
        return weeklyWeather;
    }

    public void setWeeklyWeather(List<DailyWeather> weeklyWeather) {
        this.weeklyWeather = weeklyWeather;
    }
}

class DailyWeather {
    private String date;
    private int weatherCode;
    private int weatherIcon;

    public DailyWeather(String date, int weatherCode, int weatherIcon) {
        this.date = date;
        this.weatherCode = weatherCode;
        this.weatherIcon = weatherIcon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    public int getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}