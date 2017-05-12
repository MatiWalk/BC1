package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 10/05/2017.
 */
public class LocationTest {

    Location l;

    @Test
    public void emptyConstructorTests(){
        l = new Location();
        assertEquals("empty constructor, Country error", null, l.getCountry());
        assertEquals("empty constructor, City error", null, l.getCity());
    }

    @Test
    public void parameterConstructorTests(){
        l = new Location("Argentina", "Cordoba");
        assertEquals("Full constructor, Country error", "Argentina", l.getCountry());
        assertEquals("Full constructor, City error", "Cordoba", l.getCity());
    }

    @Test
    public void setterTest(){
        l = new Location();
        l.setCountry("Mordor");
        l.setCity("Barad-Dul");
        assertEquals("Testing setters, Country error", "Mordor", l.getCountry());
        assertEquals("Testing setters, City error", "Barad-Dul", l.getCity());
    }

}
