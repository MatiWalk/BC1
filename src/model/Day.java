package model;

import java.time.LocalDate;

/**
 * Created by Mati on 01/05/2017.
 */
public class Day {

    private WeatherCode weatherCode;
    private LocalDate date;
    private int currentTemperature;
    private int lowTemperature;
    private int highTemperature;

    public Day(WeatherCode weatherCode, LocalDate date, int currentTemperature, int lowTemperature, int highTemperature) {
        this.weatherCode = weatherCode;
        this.date = date;
        this.currentTemperature = currentTemperature;
        this.lowTemperature = lowTemperature;
        this.highTemperature = highTemperature;
    }

    public WeatherCode getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(WeatherCode weatherCode) {
        this.weatherCode = weatherCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(int lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(int highTemperature) {
        this.highTemperature = highTemperature;
    }

    @Override
    public String toString() {
        return "Day{" +
                "weatherCode=" + weatherCode +
                ", date=" + date +
                ", currentTemperature=" + currentTemperature +
                ", lowTemperature=" + lowTemperature +
                ", highTemperature=" + highTemperature +
                '}';
    }
}
