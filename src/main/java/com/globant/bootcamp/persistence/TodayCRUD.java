package com.globant.bootcamp.persistence;

import com.globant.bootcamp.builder.TodayBuilder;
import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.*;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
@Component
public class TodayCRUD extends QueryExecuter implements ClimateCRUD<Today> {

    private List<Today> todays;
    private Today today;
    private ClimateR<WeatherCode> weatherCodeClimateR;
    private ClimateCRUD<Astronomy> astronomyClimateCRUD;
    private ClimateCRUD<Atmosphere> atmosphereClimateCRUD;
    private ClimateCRUD<Wind> windClimateCRUD;

    public TodayCRUD(Connection con, ClimateR<WeatherCode> weatherCodeClimateR, ClimateCRUD<Astronomy> astronomyClimateCRUD, ClimateCRUD<Atmosphere> atmosphereClimateCRUD, ClimateCRUD<Wind> windClimateCRUD) {
        super(con);
        this.weatherCodeClimateR = weatherCodeClimateR;
        this.astronomyClimateCRUD = astronomyClimateCRUD;
        this.atmosphereClimateCRUD = atmosphereClimateCRUD;
        this.windClimateCRUD = windClimateCRUD;
    }

    @Override
    public int insert(Today today) {
        int key = -1;
        String insert = "insert into today (woeid, date, idcurrentweather, " +
                "currenttemperature, idastronomy, idatmosphere, idwind) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            key=executeResult(insert, today.getWoeid(), today.getDate(), today.getCurrentWeather().getCode(), today.getCurrentTemperature(),
                    astronomyClimateCRUD.insert(today.getAstronomy()), atmosphereClimateCRUD.insert(today.getAtmosphere()),
                    windClimateCRUD.insert(today.getWind()));
        } catch (SQLException ex) {
            System.out.println("Error inserting Today:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Today today) {

        /*int[] fk = getForeignKeys(today.getId());
        astronomyClimateCRUD.update(today.getAstronomy(), fk[0]);
        atmosphereClimateCRUD.update(today.getAtmosphere(), fk[1]);
        windClimateCRUD.update(today.getWind(), fk[2]);*/
        astronomyClimateCRUD.update(today.getAstronomy());
        atmosphereClimateCRUD.update(today.getAtmosphere());
        windClimateCRUD.update(today.getWind());

        String update = " UPDATE today set date = ?, idcurrentweather = ?, currenttemperature = ? " +
                " where idtoday = ?";

        try{
            executeUpdate(update, today.getDate(), today.getCurrentWeather().getCode(),
                    today.getCurrentTemperature(), today.getId());
        } catch (SQLException ex) {
            System.out.println("Error updating Today:");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete today where idtoday = ?";
        try {
            executeDelete(delete, id);
        } catch (SQLException e) {
            System.out.println("Error deleting Today:");
            e.printStackTrace();
        }
    }

    @Override
    public Today selectByID(int id) {
        String select = "select * from today t where idtoday = ?";
        try {
            ResultSet rs = executeSelectByID(select, id);
            while (rs.next()) {
                today = TodayBuilder.builder()
                        .withID(rs.getInt(1))
                        .withWOEID(rs.getInt(2))
                        .withDate(rs.getDate(3).toLocalDate())
                        .withCurrentWeather(weatherCodeClimateR.selectByID(rs.getInt(4)))
                        .withCurrentTemperature(rs.getInt(5))
                        .withAstronomy(astronomyClimateCRUD.selectByID(rs.getInt(6)))
                        .withAtmosphere(atmosphereClimateCRUD.selectByID(rs.getInt(7)))
                        .withWind(windClimateCRUD.selectByID(rs.getInt(8)))
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting one Today:");
            e.printStackTrace();
        }
        return today;
    }

    @Override
    public List<Today> selectAll() {
        todays = new LinkedList<>();
        String select = "select * from today";
        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                today = TodayBuilder.builder()
                        .withID(rs.getInt(1))
                        .withWOEID(rs.getInt(2))
                        .withDate(rs.getDate(3).toLocalDate())
                        .withCurrentWeather(weatherCodeClimateR.selectByID(rs.getInt(4)))
                        .withCurrentTemperature(rs.getInt(5))
                        .withAstronomy(astronomyClimateCRUD.selectByID(rs.getInt(6)))
                        .withAtmosphere(atmosphereClimateCRUD.selectByID(rs.getInt(7)))
                        .withWind(windClimateCRUD.selectByID(rs.getInt(8)))
                        .build();
                todays.add(today);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting all Todays:");
            e.printStackTrace();
        }
        return todays;
    }

    @Override
    public Today selectByObject(Today today) {
        String select = "select * from today t where woeid = ? and date = ?";
        try {
            ResultSet rs = executeSelectByID(select, today.getWoeid(), today.getDate());
            while (rs.next()) {
                today = TodayBuilder.builder()
                        .withID(rs.getInt(1))
                        .withWOEID(rs.getInt(2))
                        .withDate(rs.getDate(3).toLocalDate())
                        .withCurrentWeather(weatherCodeClimateR.selectByID(rs.getInt(4)))
                        .withCurrentTemperature(rs.getInt(5))
                        .withAstronomy(astronomyClimateCRUD.selectByID(rs.getInt(6)))
                        .withAtmosphere(atmosphereClimateCRUD.selectByID(rs.getInt(7)))
                        .withWind(windClimateCRUD.selectByID(rs.getInt(8)))
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting by Today:");
            e.printStackTrace();
        }
        return today;
    }



    /*
    private int[] getForeignKeys(int id){
        int[] fk = new int[3];
        String select = "select idastronomy, idatmosphere, idwind from today where idtoday = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fk[0] = rs.getInt(1);
                fk[1] = rs.getInt(2);
                fk[2] = rs.getInt(3);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error getting foreign keys");
            ex.printStackTrace();
        }
        return fk;
    }*/
}
