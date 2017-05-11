package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 10/05/2017.
 */
public class WindTest {

    @Test
    public void emptyConstructorTests(){
        Wind w = new Wind();
        assertEquals("empty constructor, chill must be 0", 0, w.getChill());
        assertEquals("empty constructor, direction must be 0", 0, w.getDirection());
        assertEquals("empty constructor, speed must be 0", 0, w.getSpeed());
    }

    @Test
    public void parameterConstructorTests(){
        Wind w = new Wind(-10, -10, -10);
        assertEquals("Full constructor, chill must be -10", -10, w.getChill());
        assertEquals("Full constructor, direction must be -10", -10, w.getDirection());
        assertEquals("Full constructor, speed must be -10", -10, w.getSpeed());
    }

    @Test
    public void setterTest(){

    }

}
