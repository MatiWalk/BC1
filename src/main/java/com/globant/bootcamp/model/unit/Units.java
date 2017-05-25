package com.globant.bootcamp.model.unit;
import com.globant.bootcamp.model.unit.Distance;
import com.globant.bootcamp.model.unit.Pressure;
import com.globant.bootcamp.model.unit.Speed;
import com.globant.bootcamp.model.unit.Temperature;

/**
 * Created by Mati on 27/04/2017.
 */
public class Units {

    private boolean isCelsiusStandard;
    private Distance distanceUnit;
    private Pressure pressureUnit;
    private Speed speedUnit;
    private Temperature temperatureUnit;


    public Units(){
        isCelsiusStandard = true;
    }

    public Distance getDistanceUnit() {
        return distanceUnit;
    }

    public Pressure getPressureUnit() {
        return pressureUnit;
    }

    public Speed getSpeedUnit() {
        return speedUnit;
    }

    public Temperature getTemperatureUnit() {
        return temperatureUnit;
    }

    public boolean isCelsiusStandard() {
        return isCelsiusStandard;
    }

    public void setCelsiusStandard(boolean celsiusStandard) {
        isCelsiusStandard = celsiusStandard;
        if (isCelsiusStandard){
            temperatureUnit = Temperature.C;
            distanceUnit = Distance.KM;
            pressureUnit = Pressure.MB;
            speedUnit = Speed.KPH;
        }else{
            temperatureUnit = Temperature.C;
            distanceUnit = Distance.KM;
            pressureUnit = Pressure.MB;
            speedUnit = Speed.KPH;
        }
    }

    @Override
    public String toString() {
        return "Units{" +
                "distanceUnit=" + distanceUnit +
                ", pressureUnit=" + pressureUnit +
                ", speedUnit=" + speedUnit +
                ", temperatureUnit=" + temperatureUnit +
                '}';
    }
}
