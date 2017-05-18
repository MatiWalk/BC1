package com.globant.bootcamp.model;

/**
 * Created by Mati on 01/05/2017.
 */
public class WeatherCode {

    private int code;
    private String weather;

    public WeatherCode () {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherCode{" +
                "code=" + code +
                ", weather='" + weather + '\'' +
                '}';
    }

}
