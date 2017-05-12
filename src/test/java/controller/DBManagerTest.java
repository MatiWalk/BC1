package controller;

import model.*;
import model.unit.Temperature;
import org.h2.tools.RunScript;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

/**
 * Created by Mati on 11/05/2017.
 */
public class DBManagerTest {

    private static final String driver = "org.h2.Driver";
    private static final String url = "jdbc:h2:mem:test_mem";
    private static Connection con;
    private static Result r;
    private static DBManager dbm;

    private static void setConnection() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void setResult(){
        String t = "Test title";
        WeatherCode wc = new WeatherCode(1, "tropical storm");
        Day d = new Day(wc, LocalDate.of(2017, 12, 3), 20, 30, 10);;
        LinkedList<Day> ad = new LinkedList<>();
        ad.add(d);
        Location l = new Location("Test COuntry", "Test City");
        Wind w = new Wind(20, 30, 40);
        Atmosphere at = new Atmosphere(86, 0.2f, barometricPressure.RISING, 0.5f);
        Astronomy as = new Astronomy(LocalTime.NOON, LocalTime.MIDNIGHT);
        LocalDateTime pd = LocalDateTime.now();
        Units u = new Units(Temperature.C);
        r = new Result(t, ad, l, w, at, as, pd, u);
    }

    @BeforeClass
    public static void initialize(){
        setConnection();
        setResult();
        try {
            RunScript.execute(con, new FileReader("src/test/resources/weatherDatabase.sql"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        dbm = new DBManager(con);
    }

    @Test
    public void weatherCodesTest(){
        LinkedList<WeatherCode> wc = dbm.loadWeatherCodes();
        assertEquals("Error, must be same quantity", 49, wc.size());
    }

    @Test
    public void selectTest(){
        dbm.insertResult(r);
        Result inserted = dbm.loadResult(dbm.getKey());
        boolean test = true;
        //if (inserted == r) test = false;
        //assertFalse(test);
    }

}
