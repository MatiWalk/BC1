package com.globant.bootcamp.jsonDTO;

/**
 * Created by Mati on 28/05/2017.
 */
public class JsonForecast extends JsonWeatherCode {

    private String date;
    private String day;
    private int high;
    private int low;

    public JsonForecast() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    @Override
    public String toString() {
        return "com.globant.bootcamp.jsonDTO.JsonForecast{" +
                "date='" + date + '\'' +
                ", day='" + day + '\'' +
                ", high=" + high +
                ", low=" + low +
                '}';
    }
}
