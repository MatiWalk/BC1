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

    private Atmosphere(AtmosphereBuilder atmosphereBuilder) {
        this.humidity = atmosphereBuilder.humidity;
        this.pressure = atmosphereBuilder.pressure;
        this.rising = atmosphereBuilder.rising;
        this.visibility = atmosphereBuilder.visibility;
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

    public static class AtmosphereBuilder {

        int humidity;
        float pressure;
        barometricPressure rising;
        float visibility;

        public AtmosphereBuilder withHumidity(int humidity){
            this.humidity = humidity;
            return this;
        }

        public AtmosphereBuilder withPressure(float pressure){
            this.pressure = pressure;
            return this;
        }

        public AtmosphereBuilder withRising(barometricPressure rising){
            this.rising = rising;
            return this;
        }

        public AtmosphereBuilder withVisibility(float visibility){
            this.visibility = visibility;
            return this;
        }

        public Atmosphere build (){
            return new Atmosphere(this);
        }

    }

}
