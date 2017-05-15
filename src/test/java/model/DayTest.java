package model;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 10/05/2017.
 */
public class DayTest {

    Today d;

    @Test
    public void emptyConstructorTests(){
        d = new Today();
        assertEquals("empty constructor, Country error", null, d.getWeatherCode());
        assertEquals("empty constructor, City error", null, d.getDate());
        assertEquals("empty constructor, City error", 0, d.getCurrentTemperature());
        assertEquals("empty constructor, City error", 0, d.getHighTemperature());
        assertEquals("empty constructor, City error", 0, d.getLowTemperature());
    }

    @Test
    public void parameterConstructorTests(){
        WeatherCode w = new WeatherCode(1, "sunny");
        d = new Today(w, LocalDate.MIN, 20, 30, 10);
        assertEquals("Full constructor, Country error", w, d.getWeatherCode());
        assertEquals("Full constructor, City error", LocalDate.MIN, d.getDate());
        assertEquals("Full constructor, City error", 20, d.getCurrentTemperature());
        assertEquals("Full constructor, City error", 30, d.getHighTemperature());
        assertEquals("Full constructor, City error", 10, d.getLowTemperature());
    }

    @Test
    public void setterTest(){
        WeatherCode w = new WeatherCode(1, "sunny");
        d = new Today();
        d.setWeatherCode(w);
        d.setDate(LocalDate.MAX);
        d.setCurrentTemperature(20);
        d.setHighTemperature(30);
        d.setLowTemperature(10);
        assertEquals("Testing setters, Country error", w, d.getWeatherCode());
        assertEquals("Testing setters, City error", LocalDate.MAX, d.getDate());
        assertEquals("Testing setters, City error", 20, d.getCurrentTemperature());
        assertEquals("Testing setters, City error", 30, d.getHighTemperature());
        assertEquals("Testing setters, City error", 10, d.getLowTemperature());
    }

}
