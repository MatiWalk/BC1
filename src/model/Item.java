package model;

import java.time.LocalDateTime;
import java.util.Vector;

/**
 * Created by Mati on 27/04/2017.
 */
public class Item {

    String title;
    String link;
    String description;
    Guid guid;
    LocalDateTime pubDate;
    float geoLat;
    float geoLong;
    Today todayWeather;
    Vector<ForecastDay> forecastWeather;

    public Item() {
    }

    public Item(String title, String link, String description, Guid guid, LocalDateTime pubDate, float geoLat,
                float geoLong, Today todayWeather, Vector<ForecastDay> forecastWeather) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.guid = guid;
        this.pubDate = pubDate;
        this.geoLat = geoLat;
        this.geoLong = geoLong;
        this.todayWeather = todayWeather;
        this.forecastWeather = forecastWeather;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Guid getGuid() {
        return guid;
    }

    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    public float getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(float geoLat) {
        this.geoLat = geoLat;
    }

    public float getGeoLong() {
        return geoLong;
    }

    public void setGeoLong(float geoLong) {
        this.geoLong = geoLong;
    }

    public Today getTodayWeather() {
        return todayWeather;
    }

    public void setTodayWeather(Today todayWeather) {
        this.todayWeather = todayWeather;
    }

    public Vector<ForecastDay> getForecastWeather() {
        return forecastWeather;
    }

    public void setForecastWeather(Vector<ForecastDay> forecastWeather) {
        this.forecastWeather = forecastWeather;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", guid=" + guid +
                ", pubDate=" + pubDate +
                ", geoLat=" + geoLat +
                ", geoLong=" + geoLong +
                ", todayWeather=" + todayWeather +
                ", forecastWeather=" + forecastWeather +
                '}';
    }
}
