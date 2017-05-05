package model;

import java.time.LocalDate;

/**
 * Created by Mati on 01/05/2017.
 */
public class Day {

    private WeatherCode weatherCode;
    private LocalDate date;
    private int currentTemperature;

    private int highTemperature;
    private int lowTemperature;

    public Day() {
    }

    public Day(WeatherCode weatherCode, LocalDate date, int currentTemperature, int highTemperature,  int lowTemperature) {
        this.weatherCode = weatherCode;
        this.date = date;
        this.currentTemperature = currentTemperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
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
        return "model.Day{" +
                "weatherCode=" + weatherCode +
                ", date=" + date +
                ", currentTemperature=" + currentTemperature +
                ", highTemperature=" + highTemperature +
                ", lowTemperature=" + lowTemperature +
                '}';
    }
}
