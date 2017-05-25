package com.globant.bootcamp.builder;

import com.globant.bootcamp.model.Wind;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class WindBuilder {

    private int id;
    private int chill;
    private int direction;
    private int speed;

    public WindBuilder() {
    }

    public static WindBuilder builder(){
        return new WindBuilder();
    }

    public WindBuilder withID(int id){
        this.id = id;
        return this;
    }

    public WindBuilder withChill (int chill){
        this.chill = chill;
        return this;
    }

    public WindBuilder withDirection (int direction){
        this.direction = direction;
        return this;
    }

    public WindBuilder withSpeed (int speed){
        this.speed = speed;
        return this;
    }

    public Wind build(){
        Wind wind = new Wind();
        wind.setId(id);
        wind.setChill(chill);
        wind.setDirection(direction);
        wind.setSpeed(speed);
        return wind;
    }
}
