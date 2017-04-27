package Model;
import org.joda.time.*;
/**
 * When the sun rises and sets
 * Created by Mati on 26/04/2017.
 */
public class Astronomy {

    LocalTime sunRise;
    LocalTime sunSet;

    public Astronomy() {
        sunRise= new LocalTime(0,0);
    }

    public Astronomy(LocalTime sunRise, LocalTime sunSet){
        this.sunRise = sunRise;
        this.sunSet = sunSet;
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

    public String toString() {
        return "Astronomy{" +
                "sunRise=" + sunRise +
                ", sunSet=" + sunSet +
                '}';
    }
}
