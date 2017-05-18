package com.globant.bootcamp.builder;

import com.globant.bootcamp.model.Units;
import com.globant.bootcamp.model.unit.Temperature;
import org.globant.bootcamp.model.unit.*;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class UnitsBuilder {
    Temperature temperature;

    public UnitsBuilder() {
    }

    public static UnitsBuilder builder(){
        return new UnitsBuilder();
    }

    public UnitsBuilder withTemperatureUnit(Temperature temperature){
        this.temperature = temperature;
        return this;
    }

    public Units build(){
        Units units = new Units();
        units.setTemperatureUnit(temperature);
        return units;
    }
}
