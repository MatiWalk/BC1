package com.globant.bootcamp.model;

/**
 * Information about the atmosphere
 * Created by Mati on 26/04/2017.
 */
public class Atmosphere {

    int id;
    private int humidity;
    private float pressure;
    private barometricPressure rising;
    private float visibility;

    public Atmosphere() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public barometricPressure getRising() {
        return rising;
    }

    public void setRising(barometricPressure rising) {
        this.rising = rising;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "com.globant.bootcamp.model.Atmosphere{" +
                "id=" + id +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", rising=" + rising +
                ", visibility=" + visibility +
                '}';
    }
}
