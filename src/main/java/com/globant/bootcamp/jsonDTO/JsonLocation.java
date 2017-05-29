package com.globant.bootcamp.jsonDTO;

/**
 * Created by Mati on 28/05/2017.
 */
public class JsonLocation {
    private String city;
    private String region;
    private String country;

    public JsonLocation() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "com.globant.bootcamp.jsonDTO.JsonLocation{" +
                "city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
