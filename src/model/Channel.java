package model;

import java.time.LocalDateTime;

/**
 * Created by Mati on 27/04/2017.
 */
public class Channel {

    String title;
    String link;
    String lenguage;
    String description;
    LocalDateTime lastBuildDate;
    int ttl;
    Location location;
    Units units;
    Wind wind;
    Atmosphere atmosphere;
    Astronomy astronomy;
    Image image;
    Item item;

    public Channel() {
    }

    public Channel(String title, String link, String lenguage, String description, LocalDateTime lastBuildDate,
                   int ttl, Location location, Units units, Wind wind, Atmosphere atmosphere, Astronomy astronomy, Image image, Item item) {
        this.title = title;
        this.link = link;
        this.lenguage = lenguage;
        this.description = description;
        this.lastBuildDate = lastBuildDate;
        this.ttl = ttl;
        this.location = location;
        this.units = units;
        this.wind = wind;
        this.atmosphere = atmosphere;
        this.astronomy = astronomy;
        this.image = image;
        this.item = item;
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

    public String getLenguage() {
        return lenguage;
    }

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(LocalDateTime lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", lenguage='" + lenguage + '\'' +
                ", description='" + description + '\'' +
                ", lastBuildDate=" + lastBuildDate +
                ", ttl=" + ttl +
                ", location=" + location +
                ", units=" + units +
                ", wind=" + wind +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomy +
                ", image=" + image +
                ", item=" + item +
                '}';
    }
}
