package com.globant.bootcamp.builder;


import com.globant.bootcamp.model.*;

import java.time.LocalDateTime;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class TodayBuilder{

    int id;
    private LocalDateTime date;
    private WeatherCode currentWeather;
    private int currentTemperature;
    private Astronomy astronomy;
    private Atmosphere atmosphere;
    private Wind wind;

    public TodayBuilder() {
    }

    public static TodayBuilder builder(){
        return new TodayBuilder();
    }

    public TodayBuilder withID(int id){
        this.id = id;
        return this;
    }

    public TodayBuilder withDate(LocalDateTime date){
        this.date = date;
        return this;
    }

    public TodayBuilder withCurrentWeather(WeatherCode currentWeather){
        this.currentWeather = currentWeather;
        return this;
    }

    public TodayBuilder withCurrentTemperature(int currentTemperature){
        this.currentTemperature = currentTemperature;
        return this;
    }

    public TodayBuilder withAstronomy(Astronomy astronomy){
        this.astronomy = astronomy;
        return this;
    }

    public TodayBuilder withAtmosphere(Atmosphere atmosphere){
        this.atmosphere = atmosphere;
        return this;
    }

    public TodayBuilder withWind(Wind wind){
        this.wind = wind;
        return this;
    }

    public Today build(){
        Today today = new Today();
        today.setId(id);
        today.setDate(date);
        today.setCurrentWeather(currentWeather);
        today.setCurrentTemperature(currentTemperature);
        today.setAstronomy(astronomy);
        today.setAtmosphere(atmosphere);
        today.setWind(wind);
        return today;
    }



}
