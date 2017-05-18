package com.globant.bootcamp.builder;

import com.globant.bootcamp.model.WeatherCode;
import com.globant.bootcamp.model.Forecast;

import java.time.LocalDate;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class ForecastBuilder{

    WeatherCode forecastWeather;
    LocalDate date;
    int highTemperature;
    int lowTemperature;

    public ForecastBuilder() {
    }


    public static ForecastBuilder builder(){
        return new ForecastBuilder();
    }

    public ForecastBuilder withForecastWeather(WeatherCode forecastWeather){
        this.forecastWeather = forecastWeather;
        return this;
    }

    public ForecastBuilder withDate(LocalDate date){
        this.date = date;
        return this;
    }

    public ForecastBuilder withHighTemperature(int highTemperature){
        this.highTemperature = highTemperature;
        return this;
    }

    public ForecastBuilder withLowTemperature(int lowTemperature){
        this.lowTemperature = lowTemperature;
        return this;
    }

    public Forecast build(){
        Forecast forecast = new Forecast();
        forecast.setForecastWeather(forecastWeather);
        forecast.setDate(date);
        forecast.setHighTemperature(highTemperature);
        forecast.setLowTemperature(lowTemperature);
        return forecast;
    }


}
