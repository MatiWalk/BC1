package model;

import java.time.LocalDateTime;

/**
 * Created by Mati on 15/05/2017.
 */
public class Forecast {

    private WeatherCode forecastWeather;
    private LocalDateTime date;
    private int highTemperature;
    private int lowTemperature;

    public WeatherCode getForecastWeather() {
        return forecastWeather;
    }

    public void setForecastWeather(WeatherCode forecastWeather) {
        this.forecastWeather = forecastWeather;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
}
