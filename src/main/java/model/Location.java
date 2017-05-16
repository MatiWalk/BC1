package model;

/**
 * Created by Mati on 01/05/2017.
 */
public class Location {

    private String country;
    private String zone;
    private String city;

    private Location(Builder locationBuilder) {
        this.country = locationBuilder.country;
        this.zone = locationBuilder.zone;
        this.city = locationBuilder.city;
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

    public static class Builder {

        private String country;
        private String zone;
        private String city;

        public Builder() {
        }

        public Builder withCountry(String country){
            this.country = country;
            return this;
        }

        public Builder withZone(String zone){
            this.zone = zone;
            return this;
        }

        public Builder withCity(String city){
            this.city = city;
            return this;
        }

        public Location build(){
            return new Location(this);
        }
    }
}
