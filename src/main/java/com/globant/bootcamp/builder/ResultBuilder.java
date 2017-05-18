package com.globant.bootcamp.builder;

import com.globant.bootcamp.model.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Sistemas on 17/5/2017.
 */
public class ResultBuilder {
    private String title;
    private Location location;
    private Today today;
    private List<Forecast> forecasts;
    private LocalDateTime pubDate;
    private Units units;

    public ResultBuilder() {
    }

    public static ResultBuilder builder(){
        return new ResultBuilder();
    }

    public ResultBuilder withTitle(String title){
        this.title = title;
        return this;
    }

    public ResultBuilder withLocation(Location location){
        this.location = location;
        return this;
    }

    public ResultBuilder withToday(Today today){
        this.today = today;
        return this;
    }

    public ResultBuilder withForecasts(List<Forecast> forecasts){
        this.forecasts = forecasts;
        return this;
    }

    public ResultBuilder withPubDate(LocalDateTime pubDate){
        this.pubDate = pubDate;
        return this;
    }

    public ResultBuilder withUnits(Units units){
        this.units = units;
        return this;
    }

    public Result build(){
        Result result = new Result();
        result.setTitle(title);
        result.setLocation(location);
        result.setToday(today);
        result.setForecasts(forecasts);
        result.setPuDate(pubDate);
        result.setUnits(units);
        return result;
    }

}
