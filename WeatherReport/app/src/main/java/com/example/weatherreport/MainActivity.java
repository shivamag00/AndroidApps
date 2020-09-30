package com.example.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView cityView;
    private TextView temperatureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Weather weather = QueryUtils.fetchWeather();
        cityView.setText(weather.getCity());
        temperatureView.setText(weather.getTemperature());
    }
}