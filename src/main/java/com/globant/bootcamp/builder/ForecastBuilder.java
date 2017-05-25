package com.globant.bootcamp.builder;

import com.globant.bootcamp.model.WeatherCode;
import com.globant.bootcamp.model.Forecast;

import java.time.LocalDate;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class ForecastBuilder{

    private int id;
    private int woeid;
    private WeatherCode forecastWeather;
    private LocalDate date;
    private int highTemperature;
    private int lowTemperature;

    public ForecastBuilder() {
    }



    public static ForecastBuilder builder(){
        return new ForecastBuilder();
    }

    public ForecastBuilder withForecastWeather(WeatherCode forecastWeather){
        this.forecastWeather = forecastWeather;
        return this;
    }

    public ForecastBuilder withWOEID(int woeid){
        this.woeid = woeid;
        return this;
    }

    public ForecastBuilder withID(int id){
        this.id = id;
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
        forecast.setId(id);
        forecast.setWoeid(woeid);
        forecast.setForecastWeather(forecastWeather);
        forecast.setDate(date);
        forecast.setHighTemperature(highTemperature);
        forecast.setLowTemperature(lowTemperature);
        return forecast;
    }


}
