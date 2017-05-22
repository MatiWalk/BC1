package com.globant.bootcamp.model;
import java.time.LocalTime;

/**
 * When the sun rises and sets
 * Created by Mati on 26/04/2017.
 */
public class Astronomy {

    int id;
    private LocalTime sunRise;
    private LocalTime sunSet;

    public Astronomy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "com.globant.bootcamp.model.Astronomy{" +
                "id=" + id +
                ", sunRise=" + sunRise +
                ", sunSet=" + sunSet +
                '}';
    }
}


