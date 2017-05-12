package model;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 10/05/2017.
 */
public class AstronomyTest {

    Astronomy as;

    @Test
    public void emptyConstructorTests(){
        as = new Astronomy();
        assertEquals("empty constructor, Sunrise error", null, as.getSunRise());
        assertEquals("empty constructor, SunSet error", null, as.getSunSet());
    }

    @Test
    public void parameterConstructorTests(){
        as = new Astronomy(LocalTime.NOON, LocalTime.MIDNIGHT);
        assertEquals("Full constructor, Sunrise error", LocalTime.NOON, as.getSunRise());
        assertEquals("Full constructor, SunSet error", LocalTime.MIDNIGHT, as.getSunSet());
    }

    @Test
    public void setterTest(){
        as = new Astronomy();
        as.setSunRise(LocalTime.NOON);
        as.setSunSet(LocalTime.MIDNIGHT);
        assertEquals("Testing setters, Sunrise error", LocalTime.NOON, as.getSunRise());
        assertEquals("Testing setters, SunSet error", LocalTime.MIDNIGHT, as.getSunSet());
    }

}
