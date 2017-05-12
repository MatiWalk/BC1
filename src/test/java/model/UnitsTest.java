package model;

import model.unit.Distance;
import model.unit.Pressure;
import model.unit.Speed;
import model.unit.Temperature;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 10/05/2017.
 */
public class UnitsTest {

    Units u;

    @Test
    public void emptyConstructorTests(){
        u = new Units();
        assertEquals("empty constructor, Distance error", null, u.getDistanceUnit());
        assertEquals("empty constructor, Pressure error", null, u.getPressureUnit());
        assertEquals("empty constructor, Speed error", null, u.getSpeedUnit());
        assertEquals("empty constructor, Temperature error", null, u.getTemperatureUnit());

    }

    @Test
    public void parameterConstructorTests(){
        u = new Units(Temperature.C);
        assertEquals("Full constructor, Distance error", Distance.KM, u.getDistanceUnit());
        assertEquals("Full constructor, Pressure error", Pressure.MB, u.getPressureUnit());
        assertEquals("Full constructor, Speed error", Speed.KPH, u.getSpeedUnit());
        assertEquals("Full constructor, Temperature error", Temperature.C, u.getTemperatureUnit());
    }

    @Test
    public void setterTest(){
        u = new Units();
        u.setTemperatureUnit(Temperature.C);
        assertEquals("Full constructor, Distance error", Distance.KM, u.getDistanceUnit());
        assertEquals("Full constructor, Pressure error", Pressure.MB, u.getPressureUnit());
        assertEquals("Full constructor, Speed error", Speed.KPH, u.getSpeedUnit());
        assertEquals("Full constructor, Temperature error", Temperature.C, u.getTemperatureUnit());
    }

}
