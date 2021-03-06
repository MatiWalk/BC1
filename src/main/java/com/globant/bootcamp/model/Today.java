package com.globant.bootcamp.model;

//import TodayBuilder;

import java.time.LocalDate;

/**
 * Created by Mati on 01/05/2017.
 */
public class Today{

    private int id;
    private int woeid;
    private LocalDate date;
    private WeatherCode currentWeather;
    private int currentTemperature;
    private Astronomy astronomy;
    private Atmosphere atmosphere;
    private Wind wind;

    public Today() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public WeatherCode getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(WeatherCode currentWeather) {
        this.currentWeather = currentWeather;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "com.globant.bootcamp.model.Today{" +
                "id=" + id +
                ", woeid=" + woeid +
                ", date=" + date +
                ", currentWeather=" + currentWeather +
                ", currentTemperature=" + currentTemperature +
                ", astronomy=" + astronomy +
                ", atmosphere=" + atmosphere +
                ", wind=" + wind +
                '}';
    }
}
