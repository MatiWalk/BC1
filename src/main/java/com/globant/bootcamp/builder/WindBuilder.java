package com.globant.bootcamp.builder;

import com.globant.bootcamp.model.Wind;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class WindBuilder {

    int chill;
    int direction;
    int speed;

    public WindBuilder() {
    }

    public static WindBuilder builder(){
        return new WindBuilder();
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
        Wind w = new Wind();
        w.setChill(chill);
        w.setDirection(direction);
        w.setSpeed(speed);
        return w;
    }
}
