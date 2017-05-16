package model;

import java.time.LocalDate;

/**
 * Created by Mati on 15/05/2017.
 */
public class Forecast {

    protected WeatherCode forecastWeather;
    protected LocalDate date;
    protected int highTemperature;
    protected int lowTemperature;

    protected Forecast(Builder forecastBuilder) {
        this.forecastWeather = forecastBuilder.forecastWeather;
        this.date = forecastBuilder.date;
        this.highTemperature = forecastBuilder.highTemperature;
        this.lowTemperature = forecastBuilder.lowTemperature;
    }

    public WeatherCode getForecastWeather() {
        return forecastWeather;
    }

    public void setForecastWeather(WeatherCode forecastWeather) {
        this.forecastWeather = forecastWeather;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(int highTemperature) {
        this.highTemperature = highTemperature;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(int lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    @Override
    public String toString() {
        return "model.Forecast{" +
                "forecastWeather=" + forecastWeather +
                ", date=" + date +
                ", highTemperature=" + highTemperature +
                ", lowTemperature=" + lowTemperature +
                '}';
    }

    public static class Builder <T extends Builder<T>> {

        WeatherCode forecastWeather;
        LocalDate date;
        int highTemperature;
        int lowTemperature;

        public Builder() {
        }

        public Builder withForecastWeather(WeatherCode forecastWeather){
            this.forecastWeather = forecastWeather;
            return this;
        }

        public Builder withDate(LocalDate date){
            this.date = date;
            return this;
        }

        public Builder withHighTemperature(int highTemperature){
            this.highTemperature = highTemperature;
            return this;
        }

        public Builder withLowTemperature(int lowTemperature){
            this.lowTemperature = lowTemperature;
            return this;
        }

        public Forecast build(){
            return new Forecast(this);
        }

    }
}
