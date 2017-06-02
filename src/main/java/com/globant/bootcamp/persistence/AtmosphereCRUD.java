package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.Atmosphere;
import com.globant.bootcamp.builder.AtmosphereBuilder;
import com.globant.bootcamp.model.barometricPressure;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
@Component
public class AtmosphereCRUD extends QueryExecuter implements ClimateCRUD<Atmosphere> {

    public AtmosphereCRUD(Connection con) {
        super(con);
    }

    @Override
    public int insert(Atmosphere atmosphere) {
        int key = -1;
        String insert = " insert into atmosphere (humidity, pressure, rising, visibility) values (?, ?, ?, ?)";
        try {
            key=executeResult(insert, atmosphere.getHumidity(), atmosphere.getPressure(), atmosphere.getRising(), atmosphere.getVisibility());
        } catch (SQLException ex) {
            System.out.println("Error inserting Atmosphere:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public boolean update(Atmosphere atmosphere) {
        boolean gotInserted = false;
        String update = " UPDATE atmosphere set humidity = ?, pressure = ?, rising = ?, visibility = ? where idatmosphere = ?";
        try{
            executeUpdate(update, atmosphere.getHumidity(), atmosphere.getPressure(), atmosphere.getRising(),
                    atmosphere.getVisibility(), atmosphere.getId());
            gotInserted = true;
        } catch (SQLException ex) {
            System.out.println("Error updating Atmosphere:");
            ex.printStackTrace();
        }
        return gotInserted;
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete atmosphere where idatmosphere = ?";
        try {
            executeDelete(delete, id);
        } catch (SQLException e) {
            System.out.println("Error deleting Atmosphere:");
            e.printStackTrace();
        }
    }

    @Override
    public Atmosphere selectByID(int id) {
        String select = "select * from atmosphere where idatmosphere = ?";
        Atmosphere atmosphere = null;
        try {
            ResultSet rs = executeSelectByID(select, id);
            while (rs.next()) {
                atmosphere = AtmosphereBuilder.builder()
                        .withID(rs.getInt(1))
                        .withHumidity(rs.getInt(2))
                        .withPressure(rs.getFloat(3))
                        .withRising(barometricPressure.values()[rs.getInt(4)])
                        .withVisibility(rs.getFloat(5))
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting one Atmosphere:");
            e.printStackTrace();
        }

        return atmosphere;
    }

    @Override
    public List<Atmosphere> selectAll() {
        List<Atmosphere> atmospheres = new LinkedList<>();

        String select = "select * from atmosphere";
        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                Atmosphere atmosphere = AtmosphereBuilder.builder()
                        .withID(rs.getInt(1))
                        .withHumidity(rs.getInt(2))
                        .withPressure(rs.getFloat(3))
                        .withRising(barometricPressure.values()[rs.getInt(4)])
                        .withVisibility(rs.getFloat(5))
                        .build();
                atmospheres.add(atmosphere);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting all Atmosphere:");
            e.printStackTrace();
        }
        return atmospheres;
    }

    public Atmosphere selectByObject(Atmosphere atmosphereInput) {
        String select = "select * from atmosphere where humidity = ? and pressure = ? and rising = ? and visibility = ?";
        Atmosphere atmosphere = null;
        try {
            ResultSet rs = executeSelectBySomething(select, atmosphereInput.getHumidity(), atmosphereInput.getPressure(),
                    atmosphereInput.getRising(), atmosphereInput.getVisibility());
            while (rs.next()) {
                atmosphere = AtmosphereBuilder.builder()
                        .withID(rs.getInt(1))
                        .withHumidity(rs.getInt(2))
                        .withPressure(rs.getFloat(3))
                        .withRising(barometricPressure.values()[rs.getInt(4)])
                        .withVisibility(rs.getFloat(5))
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting by Atmosphere:");
            e.printStackTrace();
        }
        return atmosphere;
    }
}
