package com.globant.bootcamp.builder;

import com.globant.bootcamp.model.Location;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class LocationBuilder {

    int id;
    private String country;
    private String zone;
    private String city;

    public LocationBuilder() {
    }

    public LocationBuilder withID(int id){
        this.id = id;
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

    public Location build(){
        Location location = new Location();
        location.setId(id);
        location.setCountry(country);
        location.setZone(zone);
        location.setCity(city);
        return location;
    }

}
