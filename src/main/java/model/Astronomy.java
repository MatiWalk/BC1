package model;
import java.time.LocalTime;

/**
 * When the sun rises and sets
 * Created by Mati on 26/04/2017.
 */
public class Astronomy {

    LocalTime sunRise;
    LocalTime sunSet;

    private Astronomy(Builder astronomyBuilder) {
        sunRise = astronomyBuilder.sunRise;
        sunSet = astronomyBuilder.sunSet;
    }

    public LocalTime getSunRise() {
        return sunRise;
    }

    public void setSunRise(LocalTime sunRise) {
        this.sunRise = sunRise;
    }

    public LocalTime getSunSet() {
        return sunSet;
    }

    public void setSunSet(LocalTime sunSet) {
        this.sunSet = sunSet;
    }

    @Override
    public String toString() {
        return "model.Astronomy{" +
                "sunRise=" + sunRise +
                ", sunSet=" + sunSet +
                '}';
    }


    public static class Builder {

        LocalTime sunSet;
        LocalTime sunRise;

        public Builder() {
        }


        public Builder withSunrise(LocalTime sunRise){
            sunRise = sunRise;
            return this;
        }

        public Builder withSunset(LocalTime sunSet){
            sunSet = sunSet;
            return this;
        }

        public Astronomy build(){
            return new Astronomy(this);
        }

    }
}


