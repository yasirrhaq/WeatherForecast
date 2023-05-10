package com.example.weatherpredictor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WeeklyWeatherAdapter extends RecyclerView.Adapter<WeeklyWeatherAdapter.WeeklyWeatherViewHolder> {

    private List<DailyWeather> weeklyWeather = new ArrayList<>();

    public void setWeeklyWeather(List<DailyWeather> weeklyWeather) {
        this.weeklyWeather = weeklyWeather;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeeklyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weekly_weather, parent, false);
        return new WeeklyWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyWeatherViewHolder holder, int position) {
        DailyWeather dailyWeather = weeklyWeather.get(position);
        holder.bind(dailyWeather);
    }

    @Override
    public int getItemCount() {
        return weeklyWeather.size();
    }

    public class WeeklyWeatherViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDate;
        private TextView tvCondition;
        private ImageView ivWeatherIcon;

        public WeeklyWeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvCondition = itemView.findViewById(R.id.tvCondition);
            ivWeatherIcon = itemView.findViewById(R.id.ivWeatherIcon);
        }

        public void bind(DailyWeather dailyWeather) {
            tvDate.setText(DateUtils.getFormattedDate(dailyWeather.getDate()));
            ivWeatherIcon.setImageResource(dailyWeather.getWeatherIcon());
            tvCondition.setText(WeatherUtils.getWeatherText(dailyWeather.getWeatherCode()));
        }
    }
}
