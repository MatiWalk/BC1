package com.globant.bootcamp.model;

import java.time.LocalDate;

/**
 * Created by Mati on 15/05/2017.
 */
public class Forecast {

    private int id;
    private int woeid;
    protected WeatherCode forecastWeather;
    protected LocalDate date;
    protected int highTemperature;
    protected int lowTemperature;

    public Forecast() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWoeid() {
        return woeid;
    }

    public void setWoeid(int woeid) {
        this.woeid = woeid;
    }

    public WeatherCode getForecastWeather() {
        return forecastWeather;
    }

    public void setForecastWeather(WeatherCode forecastWeather) {
        this.forecastWeather = forecastWeather;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(int highTemperature) {
        this.highTemperature = highTemperature;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(int lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    @Override
    public String toString() {
        return "com.globant.bootcamp.model.Forecast{" +
                "id=" + id +
                ", woeid=" + woeid +
                ", forecastWeather=" + forecastWeather +
                ", date=" + date +
                ", highTemperature=" + highTemperature +
                ", lowTemperature=" + lowTemperature +
                '}';
    }
}
