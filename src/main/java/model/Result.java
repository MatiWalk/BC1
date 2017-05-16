package model;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Mati on 01/05/2017.
 */
public class Result {

    String title;
    Location location;
    Today today;
    List<Forecast> forecasts = new LinkedList<>();
    LocalDateTime puDate;
    Units units;

    private Result(Builder resultBuilder) {
        this.title = resultBuilder.title;
        this.location = resultBuilder.location;
        this.today = resultBuilder.today;
        this.forecasts = resultBuilder.forecasts;
        this.puDate = resultBuilder.pubDate;
        this.units = resultBuilder.units;
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
        return "model.Result{" +
                "title='" + title + '\'' +
                ", location=" + location +
                ", today=" + today +
                ", forecasts=" + forecasts +
                ", puDate=" + puDate +
                ", units=" + units +
                '}';
    }

    public static class Builder {

        private String title;
        private Location location;
        private Today today;
        private List<Forecast> forecasts;
        private LocalDateTime pubDate;
        private Units units;

        public Builder() {
        }

        public Builder withTitle(String title){
            this.title = title;
            return this;
        }

        public Builder withLocation(Location location){
            this.location = location;
            return this;
        }

        public Builder withToday(Today today){
            this.today = today;
            return this;
        }

        public Builder withForecasts(List<Forecast> forecasts){
            this.forecasts = forecasts;
            return this;
        }

        public Builder withPubDate(LocalDateTime pubDate){
            this.pubDate = pubDate;
            return this;
        }

        public Builder withUnits(Units units){
            this.units = units;
            return this;
        }

        public Result build(){
            return new Result(this);
        }
    }
}
