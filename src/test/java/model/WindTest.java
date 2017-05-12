package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 10/05/2017.
 */
public class WindTest {

    Wind w;

    @Test
    public void emptyConstructorTests(){
        w = new Wind();
        assertEquals("empty constructor, chill error", 0, w.getChill());
        assertEquals("empty constructor, direction error", 0, w.getDirection());
        assertEquals("empty constructor, speed error", 0, w.getSpeed());
    }

    @Test
    public void parameterConstructorTests(){
        w = new Wind(-10, -10, -10);
        assertEquals("Full constructor, chill error", -10, w.getChill());
        assertEquals("Full constructor, direction error", -10, w.getDirection());
        assertEquals("Full constructor, speed error", -10, w.getSpeed());
    }

    @Test
    public void setterTest(){
        w = new Wind();
        w.setChill(8);
        w.setDirection(8);
        w.setSpeed(8);
        assertEquals("Testing setters, chill error", 8, w.getChill());
        assertEquals("Testing setters, direction error", 8, w.getDirection());
        assertEquals("Testing setters, speed error", 8, w.getSpeed());
    }

}
