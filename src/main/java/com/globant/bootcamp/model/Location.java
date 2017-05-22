package com.globant.bootcamp.model;

/**
 * Created by Mati on 01/05/2017.
 */
public class Location {

    int id;
    private String country;
    private String zone;
    private String city;

    public Location() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "com.globant.bootcamp.model.Location{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", zone='" + zone + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
