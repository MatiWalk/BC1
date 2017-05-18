package model;

//import builder.TodayBuilder;

/**
 * Created by Mati on 01/05/2017.
 */
public class Today extends Forecast{

    private WeatherCode currentWeather;
    private int currentTemperature;
    private Astronomy astronomy;
    private Atmosphere atmosphere;
    private Wind wind;

    public Today() {
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

}
