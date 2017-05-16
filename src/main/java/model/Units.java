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


    private Units(Builder unitsBuilder){
        this.setTemperatureUnit(unitsBuilder.temperature);
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
        return "model.Units{" +
                "distanceUnit=" + distanceUnit +
                ", pressureUnit=" + pressureUnit +
                ", speedUnit=" + speedUnit +
                ", temperatureUnit=" + temperatureUnit +
                '}';
    }

    public static class Builder {
        Temperature temperature;

        public Builder withTemperatureUnit(Temperature temperature){
            this.temperature = temperature;
            return this;
        }

        public Units build (){
            return new Units(this);
        }
    }
}
