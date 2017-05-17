package builder;

import model.Units;
import model.unit.*;

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
