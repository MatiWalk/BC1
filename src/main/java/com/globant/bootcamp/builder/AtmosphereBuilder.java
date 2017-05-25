package com.globant.bootcamp.builder;

import com.globant.bootcamp.model.Atmosphere;
import com.globant.bootcamp.model.barometricPressure;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class AtmosphereBuilder {

    private int id;
    private int humidity;
    private float pressure;
    private barometricPressure rising;
    private float visibility;

    public AtmosphereBuilder() {
    }

    public AtmosphereBuilder withID(int id){
        this.id = id;
        return this;
    }

    public static AtmosphereBuilder builder(){
        return new AtmosphereBuilder();
    }

    public AtmosphereBuilder withHumidity(int humidity){
        this.humidity = humidity;
        return this;
    }

    public AtmosphereBuilder withPressure(float pressure){
        this.pressure = pressure;
        return this;
    }

    public AtmosphereBuilder withRising(barometricPressure rising){
        this.rising = rising;
        return this;
    }

    public AtmosphereBuilder withVisibility(float visibility){
        this.visibility = visibility;
        return this;
    }

    public Atmosphere build (){
        Atmosphere atmosphere = new Atmosphere();
        atmosphere.setId(id);
        atmosphere.setHumidity(humidity);
        atmosphere.setPressure(pressure);
        atmosphere.setRising(rising);
        atmosphere.setVisibility(visibility);
        return atmosphere;
    }
}
