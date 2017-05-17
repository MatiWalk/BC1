package model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 10/05/2017.
 */
public class WeatherCodeTest {

    WeatherCode wc;

    @Test
    public void emptyConstructorTests(){
        wc = new WeatherCode();
        assertEquals("empty constructor, code error", 0, wc.getCode());
        assertEquals("empty constructor, weather error", null, wc.getWeather());
    }

    @Test
    public void parameterConstructorTests(){
        wc = new WeatherCode(2, "cloudy");
        assertEquals("Full constructor, code error", 2, wc.getCode());
        assertEquals("Full constructor, weather error", "cloudy", wc.getWeather());
    }

    @Test
    public void setterTest(){
        wc = new WeatherCode();
        wc.setCode(8);
        wc.setWeather("Sunny");
        assertEquals("Testing setters, code error", 8, wc.getCode());
        assertEquals("Testing setters, weather error", "Sunny", wc.getWeather());
    }

}
