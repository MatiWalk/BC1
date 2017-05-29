package com.globant.bootcamp.jsonDTO;

/**
 * Created by Mati on 28/05/2017.
 */
public class JsonAtmosphere {
    private int humidity;
    private float pressure;
    private int rising;
    private float visibility;

    public JsonAtmosphere() {
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

    public int getRising() {
        return rising;
    }

    public void setRising(int rising) {
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
        return "com.globant.bootcamp.jsonDTO.JsonAtmosphere{" +
                "humidity=" + humidity +
                ", pressure=" + pressure +
                ", rising=" + rising +
                ", visibility=" + visibility +
                '}';
    }
}
