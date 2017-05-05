package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Mati on 01/05/2017.
 */
public class Result {

    String title;
    ArrayList<Day> Days;
    Location location;
    Wind wind;
    Atmosphere atmosphere;
    Astronomy astronomy;
    LocalDateTime puDate;
    Units units;

    public Result() {
    }

    public Result(String title, ArrayList<Day> days, Location location, Wind wind, Atmosphere atmosphere,
                  Astronomy astronomy, LocalDateTime puDate, Units units) {
        this.title = title;
        Days = days;
        this.location = location;
        this.wind = wind;
        this.atmosphere = atmosphere;
        this.astronomy = astronomy;
        this.puDate = puDate;
        this.units = units;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Day> getDays() {
        return Days;
    }

    public void setDays(ArrayList<Day> days) {
        Days = days;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public LocalDateTime getPuDate() { return puDate; }

    public void setPuDate(LocalDateTime puDate) { this.puDate = puDate; }

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
                ", Days=" + Days +
                ", location=" + location +
                ", wind=" + wind +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomy +
                ", puDate=" + puDate +
                ", units=" + units +
                '}';
    }
}
