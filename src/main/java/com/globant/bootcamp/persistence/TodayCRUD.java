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
        Today today = null;
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
        List<Today> todays = new LinkedList<>();
        String select = "select * from today";
        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                Today today = TodayBuilder.builder()
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
    public Today selectByObject(Today todayInput) {
        String select = "select * from today t where woeid = ? and date = ?";
        Today today = null;
        try {
            ResultSet rs = executeSelectByID(select, todayInput.getWoeid(), todayInput.getDate());
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
}
