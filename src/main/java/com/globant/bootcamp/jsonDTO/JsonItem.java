package com.globant.bootcamp.jsonDTO;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;


/**
 * Created by Mati on 28/05/2017.
 */
public class JsonItem {
    private String title;
    private String lat;
    @JsonProperty ("long")
    private String longitude;
    private String link;
    private String pubDate;
    private JsonCondition condition;
    private List<JsonForecast> forecast;
    private String description;
    @JsonIgnore
    private Object guid;

    public JsonItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public JsonCondition getCondition() {
        return condition;
    }

    public void setCondition(JsonCondition condition) {
        this.condition = condition;
    }

    public List<JsonForecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<JsonForecast> forecast) {
        this.forecast = forecast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getGuid() {
        return guid;
    }

    public void setGuid(Object guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "com.globant.bootcamp.jsonDTO.JsonItem{" +
                "title='" + title + '\'' +
                ", lat='" + lat + '\'' +
                ", longitude='" + longitude + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", condition=" + condition +
                ", forecast=" + forecast +
                ", description='" + description + '\'' +
                ", guid=" + guid +
                '}';
    }
}
