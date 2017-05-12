package model;

import model.unit.Temperature;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 10/05/2017.
 */
public class ResultTest {

    static Result r;
    static String t;
    static WeatherCode wc;
    static Day d;
    static LinkedList<Day> ad;
    static Location l;
    static Wind w;
    static Atmosphere at;
    static Astronomy as;
    static LocalDateTime pd;
    static Units u;

    @BeforeClass
    public static void initialize(){
        ad = new LinkedList<>();
        t = "Test title";
        wc = new WeatherCode(1, "Sunny");
        d = new Day(wc, LocalDate.of(2017, 12, 3), 20, 30, 10);
        ad.add(d);
        l = new Location("Test COuntry", "Test City");
        w = new Wind(20, 30, 40);
        at = new Atmosphere(86, 0.2f, barometricPressure.RISING, 0.5f);
        as = new Astronomy(LocalTime.NOON, LocalTime.MIDNIGHT);
        pd = LocalDateTime.now();
        u = new Units(Temperature.C);
    }

    @Test
    public void emptyConstructorTests(){
        r = new Result();
        assertEquals("empty constructor, Title error", null, r.getTitle());
        assertEquals("empty constructor, Days error", null, r.getDays());
        assertEquals("empty constructor, Location error", null, r.getLocation());
        assertEquals("empty constructor, Wind error", null, r.getWind());
        assertEquals("empty constructor, Atmosphere error", null, r.getAtmosphere());
        assertEquals("empty constructor, Astronomy error", null, r.getAstronomy());
        assertEquals("empty constructor, publish Date error", null, r.getPuDate());
        assertEquals("empty constructor, Units error", null, r.getUnits());

    }

    @Test
    public void parameterConstructorTests(){
        r = new Result(t, ad, l, w, at, as, pd, u);

        assertEquals("Full constructor, Title error", t, r.getTitle());
        assertEquals("Full constructor, Days error", ad, r.getDays());
        assertEquals("Full constructor, Location error", l, r.getLocation());
        assertEquals("Full constructor, Wind error", w, r.getWind());
        assertEquals("Full constructor, Atmosphere error", at, r.getAtmosphere());
        assertEquals("Full constructor, Astronomy error", as, r.getAstronomy());
        assertEquals("Full constructor, publish Date error", pd, r.getPuDate());
        assertEquals("Full constructor, Units error", u, r.getUnits());

    }

    @Test
    public void setterTest(){
        r = new Result();
        r.setTitle(t);
        r.setDays(ad);
        r.setLocation(l);
        r.setWind(w);
        r.setAtmosphere(at);
        r.setAstronomy(as);
        r.setPuDate(pd);
        r.setUnits(u);

        assertEquals("Testing setters, Title error", t, r.getTitle());
        assertEquals("Testing setters, Days error", ad, r.getDays());
        assertEquals("Testing setters, Days error", l, r.getLocation());
        assertEquals("Testing setters, Wind error", w, r.getWind());
        assertEquals("Testing setters, Atmosphere error", at, r.getAtmosphere());
        assertEquals("Testing setters, Astronomy error", as, r.getAstronomy());
        assertEquals("Testing setters, publish Date error", pd, r.getPuDate());
        assertEquals("Testing setters, Units error", u, r.getUnits());

    }

}
