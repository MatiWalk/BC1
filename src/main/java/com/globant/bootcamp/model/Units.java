package com.globant.bootcamp.model;
import com.globant.bootcamp.model.unit.Distance;
import com.globant.bootcamp.model.unit.Pressure;
import com.globant.bootcamp.model.unit.Speed;
import com.globant.bootcamp.model.unit.Temperature;

/**
 * Created by Mati on 27/04/2017.
 */
public class Units {

    private Distance distanceUnit;
    private Pressure pressureUnit;
    private Speed speedUnit;
    private Temperature temperatureUnit;


    public Units(){
    }

    public Distance getDistanceUnit() {
        return distanceUnit;
    }

    private void setDistanceUnit(Distance distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public Pressure getPressureUnit() {
        return pressureUnit;
    }

    private void setPressureUnit(Pressure pressureUnit) {
        this.pressureUnit = pressureUnit;
    }

    public Speed getSpeedUnit() {
        return speedUnit;
    }

    private void setSpeedUnit(Speed speedUnit) {
        this.speedUnit = speedUnit;
    }

    public Temperature getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(Temperature temperatureUnit) {

        this.temperatureUnit = temperatureUnit;
        if (this.temperatureUnit.equals(Temperature.F)){
            distanceUnit = Distance.MI;
            pressureUnit = Pressure.IN;
            speedUnit = Speed.MPH;
        }
        if (this.temperatureUnit.equals(Temperature.C)){
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
