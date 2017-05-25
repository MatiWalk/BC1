package com.globant.bootcamp.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Mati on 01/05/2017.
 */
public class Location {

    private int woeid;
    private String country;
    private String zone;
    private String city;
    private Today today;
    private List<Forecast> forecasts;
    private LocalDateTime lastUpdate;

    public Location() {
    }

    public int getWoeid() {
        return woeid;
    }

    public void setWoeid(int woeid) {
        this.woeid = woeid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public String toString() {
        return "com.globant.bootcamp.model.Location{" +
                "woeid=" + woeid +
                ", country='" + country + '\'' +
                ", zone='" + zone + '\'' +
                ", city='" + city + '\'' +
                ", today=" + today +
                ", forecasts=" + forecasts +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
