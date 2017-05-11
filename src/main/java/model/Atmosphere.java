package model;

/**
 * Information about the atmosphere
 * Created by Mati on 26/04/2017.
 */
public class Atmosphere {

    int humidity;
    float pressure;
    barometricPressure rising;
    float visibility;

    public Atmosphere() {
    }

    public Atmosphere(int humidity, float pressure, barometricPressure rising, float visibility) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.rising = rising;
        this.visibility = visibility;
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
        return "model.Atmosphere{" +
                "humidity=" + humidity +
                ", pressure=" + pressure +
                ", rising=" + rising +
                ", visibility=" + visibility +
                '}';
    }
}