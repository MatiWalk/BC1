package com.globant.bootcamp.builder;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.globant.bootcamp.model.Astronomy;

import java.time.LocalTime;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class AstronomyBuilder {

    int id;
    LocalTime sunRise;
    LocalTime sunSet;

    public AstronomyBuilder() {
    }

    public static AstronomyBuilder builder(){
        return new AstronomyBuilder();
    }

    public AstronomyBuilder withID(int id){
        this.id = id;
        return this;
    }

    public AstronomyBuilder withSunrise(LocalTime sunRise){
        this.sunRise = sunRise;
        return this;
    }

    public AstronomyBuilder withSunset(LocalTime sunSet){
        this.sunSet = sunSet;
        return this;
    }

    public Astronomy build(){
        Astronomy astronomy = new Astronomy();
        astronomy.setId(id);
        astronomy.setSunRise(sunRise);
        astronomy.setSunSet(sunSet);
        return astronomy;
    }

}
