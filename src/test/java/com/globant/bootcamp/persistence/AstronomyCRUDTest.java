package com.globant.bootcamp.persistence;

import com.globant.bootcamp.builder.AstronomyBuilder;
import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.Astronomy;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Sistemas on 19/5/2017.
 */
public class AstronomyCRUDTest {
    static Astronomy at1;
    static Astronomy at2;
    static Connection con;
    static ClimateCRUD<Astronomy> astronomyClimateCRUD;

    @BeforeClass
    public static void initialize(){


        at1 = AstronomyBuilder.builder()
                .withSunrise(LocalTime.NOON)
                .withSunset(LocalTime.MIDNIGHT)
                .build();
        at2 = AstronomyBuilder.builder()
                .withSunrise(LocalTime.MIDNIGHT)
                .withSunset(LocalTime.NOON)
                .build();
    }

    @Test
    public void insertTest(){
        /*id = astronomyClimateCRUD.insert(at1);
        assertEquals(1, id);*/
    }

    @Test
    public void updateTest(){
        /*astronomyClimateCRUD.update(at2, id);
        assertEquals(1, id);*/
    }

    @Test
    public void selectByIDTest(){
        /*at1 = astronomyClimateCRUD.selectByID(id);
        assertEquals(at2, at1);*/
    }

    @Test
    public void selectAllTest(){
        /*List <Astronomy> lat= astronomyClimateCRUD.selectAll();
        assertEquals(1, lat.size());*/
    }

}
