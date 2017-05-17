package model;

/**
 * Created by Mati on 01/05/2017.
 */
public class Location {

    private String country;
    private String zone;
    private String city;

    public Location() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "model.Location{" +
                "country='" + country + '\'' +
                ", Zone='" + zone + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
