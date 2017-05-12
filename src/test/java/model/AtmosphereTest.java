package model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 10/05/2017.
 */
public class AtmosphereTest {

    Atmosphere at;

    @Test
    public void emptyConstructorTests(){
        at = new Atmosphere();
        assertEquals("empty constructor, humidity error", 0, at.getHumidity());
        assertEquals("empty constructor, pressure error", 0f, at.getPressure(), 0.01);
        assertEquals("empty constructor, rising error", null, at.getRising());
        assertEquals("empty constructor, visibility error", 0f, at.getVisibility(), 0.01);
    }

    @Test
    public void parameterConstructorTests(){
        at = new Atmosphere(23, 8.5f, barometricPressure.FALLING, 58.3f);
        assertEquals("Full constructor, humidity error", 23, at.getHumidity());
        assertEquals("Full constructor, pressure error", 8.5f, at.getPressure(), 0.1);
        assertEquals("Full constructor, rising error", barometricPressure.FALLING, at.getRising());
        assertEquals("Full constructor, visibility error", 58.3f, at.getVisibility(), 0.1);
    }

    @Test
    public void setterTest(){
        at = new Atmosphere();
        at.setHumidity(54);
        at.setPressure(9.6f);
        at.setRising(barometricPressure.RISING);
        at.setVisibility(56.8f);
        assertEquals("Testing setters, humidity error", 54, at.getHumidity());
        assertEquals("Testing setters, pressure error", 9.6f, at.getPressure(), 0.1);
        assertEquals("Testing setters, rising error", barometricPressure.RISING, at.getRising());
        assertEquals("Testing setters, visibility error", 56.8f, at.getVisibility(), 0.1);
    }

}
