package model;

/**
 * Created by Mati on 01/05/2017.
 */
public class Today extends Forecast{

    private WeatherCode currentWeather;
    private int currentTemperature;
    private Astronomy astronomy;
    private Atmosphere atmosphere;
    private Wind wind;

    private Today(Builder builder) {
        super(builder);
        this.currentWeather = builder.currentWeather;
        this.currentTemperature = builder.currentTemperature;
        this.astronomy = builder.astronomy;
        this.atmosphere = builder.atmosphere;
        this.wind = builder.wind;
    }



    public WeatherCode getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(WeatherCode currentWeather) {
        this.currentWeather = currentWeather;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "model.Today{" +
                "forecastWeather=" + forecastWeather +
                ", currentWeather=" + currentWeather +
                ", currentTemperature=" + currentTemperature +
                ", date=" + date +
                ", highTemperature=" + highTemperature +
                ", astronomy=" + astronomy +
                ", lowTemperature=" + lowTemperature +
                ", atmosphere=" + atmosphere +
                ", wind=" + wind +
                '}';
    }

    public static class Builder extends Forecast.Builder<Builder> {

        private WeatherCode currentWeather;
        private int currentTemperature;
        private Astronomy astronomy;
        private Atmosphere atmosphere;
        private Wind wind;

        public Builder() {
        }

        public Builder withCurrentWeather(WeatherCode currentWeather){
            this.currentWeather = currentWeather;
            return this;
        }

        public Builder withCurrentTemperature(int currentTemperature){
            this.currentTemperature = currentTemperature;
            return this;
        }

        public Builder withCurrentWeather(Astronomy astronomy){
            this.astronomy = astronomy;
            return this;
        }

        public Builder withCurrentWeather(Atmosphere atmosphere){
            this.atmosphere = atmosphere;
            return this;
        }

        public Builder withCurrentWeather(Wind wind){
            this.wind = wind;
            return this;
        }

        public Today build(){
            return new Today(this);
        }


    }
}
