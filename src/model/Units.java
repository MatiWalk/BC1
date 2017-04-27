package model;

import model.unit.*;

/**
 * Created by Mati on 27/04/2017.
 */
public class Units {

    Distance distanceUnit;
    Pressure pressureUnit;
    Speed speedUnit;
    Temperature temperatureUnit;

    public Units() {
    }

    public Units(Distance distanceUnit, Pressure pressureUnit, Speed speedUnit, Temperature temperatureUnit) {
        this.distanceUnit = distanceUnit;
        this.pressureUnit = pressureUnit;
        this.speedUnit = speedUnit;
        this.temperatureUnit = temperatureUnit;
    }

    public Distance getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(Distance distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public Pressure getPressureUnit() {
        return pressureUnit;
    }

    public void setPressureUnit(Pressure pressureUnit) {
        this.pressureUnit = pressureUnit;
    }

    public Speed getSpeedUnit() {
        return speedUnit;
    }

    public void setSpeedUnit(Speed speedUnit) {
        this.speedUnit = speedUnit;
    }

    public Temperature getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(Temperature temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public String toString() {
        return "Units{" +
                "distanceUnit=" + distanceUnit +
                ", pressureUnit=" + pressureUnit +
                ", speedUnit=" + speedUnit +
                ", temperatureUnit=" + temperatureUnit +
                '}';
    }
}
