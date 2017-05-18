package com.globant.bootcamp.model;
import java.time.LocalTime;

/**
 * When the sun rises and sets
 * Created by Mati on 26/04/2017.
 */
public class Astronomy {

    private LocalTime sunRise;
    private LocalTime sunSet;

    public Astronomy() {
    }

    public LocalTime getSunRise() {
        return sunRise;
    }

    public void setSunRise(LocalTime sunRise) {
        this.sunRise = sunRise;
    }

    public LocalTime getSunSet() {
        return sunSet;
    }

    public void setSunSet(LocalTime sunSet) {
        this.sunSet = sunSet;
    }

    @Override
    public String toString() {
        return "Astronomy{" +
                "sunRise=" + sunRise +
                ", sunSet=" + sunSet +
                '}';
    }



}


