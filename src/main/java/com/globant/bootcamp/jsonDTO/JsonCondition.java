package com.globant.bootcamp.jsonDTO;

/**
 * Created by Mati on 28/05/2017.
 */
public class JsonCondition extends JsonWeatherCode {
    private String date;
    private int temp;

    public JsonCondition() {
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "com.globant.bootcamp.jsonDTO.JsonCondition{" +
                "date='" + date + '\'' +
                ", temp=" + temp +
                '}';
    }
}
