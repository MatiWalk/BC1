package com.globant.bootcamp.builder;

import com.globant.bootcamp.model.WeatherCode;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class WeatherCodeBuilder {
    private int code;
    private String weather;

    public WeatherCodeBuilder() {
    }

    public static WeatherCodeBuilder builder(){
        return new WeatherCodeBuilder();
    }

    public WeatherCodeBuilder withCode(int code){
        this.code = code;
        return this;
    }

    public WeatherCodeBuilder withWeather(String weather){
        this.weather = weather;
        return this;
    }

    public WeatherCode build(){
        WeatherCode weatherCode = new WeatherCode();
        weatherCode.setCode(code);
        weatherCode.setWeather(weather);
        return weatherCode;
     }
}
