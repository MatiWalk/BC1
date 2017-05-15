package controller;

import model.*;
import model.unit.Temperature;
import org.h2.tools.RunScript;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Mati on 11/05/2017.
 */
public class DBConnectorTest {

    private static final String driver = "org.h2.Driver";
    private static final String username = "root";
    private static final String password = "admin";
    private static final String url = "jdbc:h2:mem:test_mem";
    private static Result r;
    private static DBConnector dbc;


    private static void setResult(){
        String t = "Test title";
        WeatherCode wc = new WeatherCode(1, "tropical storm");
        Today d = new Today(wc, LocalDate.of(2017, 12, 3), 20, 30, 10);;
        LinkedList<Today> ad = new LinkedList<>();
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

        DBConnector.setTestStrings(driver, username, password, url);
        setResult();
        dbc = DBConnector.getInstance();

        try {
            RunScript.execute(dbc.getCon(), new FileReader("src/test/resources/TestDatabase.sql"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        dbc.loadWeatherCodes();
    }

    @Test
    public void weatherCodesTest(){
        LinkedList<WeatherCode> wc = dbc.loadWeatherCodes();
        assertEquals("Error, must be same quantity", 49, wc.size());
    }

    @Test
    public void insertResultTest(){
        dbc.insertResult(r);
        //chekcing just some of the values
        Result inserted = dbc.loadResult(1);
        assertTrue(r.getTitle().equals(inserted.getTitle()));
        assertTrue(r.getDays().size() == inserted.getDays().size());
        assertTrue(r.getPuDate().equals(inserted.getPuDate()));

    }



}
