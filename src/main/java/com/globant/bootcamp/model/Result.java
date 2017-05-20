package com.globant.bootcamp.model;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Mati on 01/05/2017.
 */
public class Result {

    private String title;
    private Location location;
    private Today today;
    private List<Forecast> forecasts = new LinkedList<>();
    private LocalDateTime puDate;
    private Units units;

    public Result() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public LocalDateTime getPuDate() {
        return puDate;
    }

    public void setPuDate(LocalDateTime puDate) {
        this.puDate = puDate;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Result{" +
                "title='" + title + '\'' +
                ", location=" + location +
                ", today=" + today +
                ", forecasts=" + forecasts +
                ", puDate=" + puDate +
                ", units=" + units +
                '}';
    }
}