package Model;
/**
 * Created by Mati on 26/04/2017.
 */
public class Location {

    //The location we are currently checking

    private String city;
    private String country;
    private String region;

    public Location() {
        city = "";
        country = "";
        region = "";
    }

    public Location(String city, String country, String region) {
        this.city = city;
        this.country = country;
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
