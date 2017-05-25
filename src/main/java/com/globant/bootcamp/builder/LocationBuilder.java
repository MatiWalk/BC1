package com.globant.bootcamp.builder;

import com.globant.bootcamp.model.Forecast;
import com.globant.bootcamp.model.Location;
import com.globant.bootcamp.model.Today;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class LocationBuilder {

    private int woeid;
    private String country;
    private String zone;
    private String city;
    private Today today;
    private List<Forecast> forecasts;
    private LocalDateTime lastUpdate;

    public LocationBuilder() {
    }

    public LocationBuilder withWoeid(int woeid){
        this.woeid = woeid;
        return this;
    }

    public static LocationBuilder builder(){
        return new LocationBuilder();
    }

    public LocationBuilder withCountry(String country){
        this.country = country;
        return this;
    }

    public LocationBuilder withZone(String zone){
        this.zone = zone;
        return this;
    }

    public LocationBuilder withCity(String city){
        this.city = city;
        return this;
    }

    public LocationBuilder withToday(Today today){
        this.today = today;
        return this;
    }

    public LocationBuilder withForecasts(List<Forecast> forecasts){
        this.forecasts = forecasts;
        return this;
    }

    public LocationBuilder withLastUpdate(LocalDateTime lastUpdate){
        this.lastUpdate = lastUpdate;
        return this;
    }

    public Location build(){
        Location location = new Location();
        location.setWoeid(woeid);
        location.setCountry(country);
        location.setZone(zone);
        location.setCity(city);
        location.setToday(today);
        location.setForecasts(forecasts);
        location.setLastUpdate(lastUpdate);
        return location;
    }

}
