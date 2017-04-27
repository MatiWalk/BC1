package model;
import model.unit.barometricPressure;

/**
 * Information about the atmosphere
 * Created by Mati on 26/04/2017.
 */
public class Atmosphere {

    int humidity;
    barometricPressure pressure;
    int rising;
    float visibility;

    public Atmosphere() {
    }

    public Atmosphere(int humidity, barometricPressure pressure, int rising, float visibility) {
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

    public barometricPressure getPressure() {
        return pressure;
    }

    public void setPressure(barometricPressure pressure) {
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

    public String toString() {
        return "Atmosphere{" +
                "humidity=" + humidity +
                ", pressure=" + pressure.toString() +
                ", rising=" + rising +
                ", visibility=" + visibility +
                '}';
    }
}
