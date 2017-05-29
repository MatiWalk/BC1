package com.globant.bootcamp.jsonDTO;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created by Mati on 28/05/2017.
 */
public class JsonChannel {

    @JsonIgnore
    private Object units;
    private String title;
    private String link;
    private String description;
    private String language;
    private String lastBuildDate;
    private String ttl;
    private JsonLocation location;
    private JsonWind wind;
    private JsonAtmosphere atmosphere;
    private JsonAstronomy astronomy;
    @JsonIgnore
    private Object image;
    private JsonItem item;

    public JsonChannel() {
    }

    public Object getUnits() {
        return units;
    }

    public void setUnits(Object units) {
        this.units = units;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public JsonLocation getLocation() {
        return location;
    }

    public void setLocation(JsonLocation location) {
        this.location = location;
    }

    public JsonWind getWind() {
        return wind;
    }

    public void setWind(JsonWind wind) {
        this.wind = wind;
    }

    public JsonAtmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(JsonAtmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public JsonAstronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(JsonAstronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public JsonItem getItem() {
        return item;
    }

    public void setItem(JsonItem item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "com.globant.bootcamp.jsonDTO.JsonChannel{" +
                "units=" + units +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", lastBuildDate='" + lastBuildDate + '\'' +
                ", ttl='" + ttl + '\'' +
                ", location=" + location +
                ", wind=" + wind +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomy +
                ", image=" + image +
                ", item=" + item +
                '}';
    }
}
