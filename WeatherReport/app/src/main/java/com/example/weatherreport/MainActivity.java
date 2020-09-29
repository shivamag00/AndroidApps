package com.example.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView cityView;
    private TextView temperatureView;
    private final float KELVIN_CONSTANT = (float) 273.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityView = (TextView) findViewById(R.id.City);
        temperatureView = (TextView) findViewById(R.id.Temperature);
        WeatherAsyncTask task = new WeatherAsyncTask();
        task.execute();
    }

    public class WeatherAsyncTask extends AsyncTask<Void, Void, Weather> {

        @Override
        protected Weather doInBackground(Void... location) {
            Weather weather = QueryUtils.fetchWeather();
            return weather;
        }

        protected void onPostExecute(Weather weather) {
            if (weather!=null) {
                cityView.setText(weather.getCity());
                temperatureView.setText(kelvinToCelsius(weather.getTemperature())+"\u2103");
            }
        }
    }

    public String kelvinToCelsius(String kelvin) {
        float celsius = (float) Float.parseFloat(kelvin);
        celsius = (float) (celsius - KELVIN_CONSTANT);
        return String.valueOf(Math.round((celsius)));
    }
}