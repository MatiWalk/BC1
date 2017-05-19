package com.globant.bootcamp.persistence;

import com.globant.bootcamp.builder.TodayBuilder;
import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class TodayCRUD implements ClimateCRUD<Today> {

    private List<Today> todays;
    private Today today;
    private Connection con = DBConnector.getInstance().getCon();
    private ClimateR<WeatherCode> weatherCodeClimateR = new WeatherCodeR();
    private ClimateCRUD<Astronomy> astronomyClimateCRUD = new AstronomyCRUD();
    private ClimateCRUD<Atmosphere> atmosphereClimateCRUD = new AtmosphereCRUD();
    private ClimateCRUD<Wind> windClimateCRUD = new WindCRUD();


    @Override
    public int insert(Today today) {
        int key = -1;
        String insert = "insert into today (date, idcurrentweather, " +
                "currenttemperature, idastronomy, idatmosphere, idwind) values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(today.getDate()));
            ps.setInt(2, today.getCurrentWeather().getCode());
            ps.setInt(3, today.getCurrentTemperature());
            ps.setInt(4, astronomyClimateCRUD.insert(today.getAstronomy()));
            ps.setInt(5, atmosphereClimateCRUD.insert(today.getAtmosphere()));
            ps.setInt(6, windClimateCRUD.insert(today.getWind()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error inserting Today:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Today today, int id) {

        int[] fk = getForeignKeys(id);
        astronomyClimateCRUD.update(today.getAstronomy(), fk[0]);
        atmosphereClimateCRUD.update(today.getAtmosphere(), fk[1]);
        windClimateCRUD.update(today.getWind(), fk[2]);

        String update = " UPDATE today set date = ?, idcurrentweather = ?, currenttemperature = ?, " +
                "idastronomy = ?, idatmosphere = ?, idwind = ? where idtoday = ?";
        try {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setTimestamp(1, Timestamp.valueOf(today.getDate()));
            ps.setInt(2, today.getCurrentWeather().getCode());
            ps.setInt(3, today.getCurrentTemperature());
            ps.setInt(4, astronomyClimateCRUD.insert(today.getAstronomy()));
            ps.setInt(5, atmosphereClimateCRUD.insert(today.getAtmosphere()));
            ps.setInt(6, windClimateCRUD.insert(today.getWind()));
            ps.setInt(7, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error updating Forecast:");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete today where idforecast = ?";
        try {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error deleting Today:");
            ex.printStackTrace();
        }
    }

    @Override
    public Today selectByID(int id) {
        String select = "select * from today t where idtoday = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                today = TodayBuilder.builder()
                        .withDate(rs.getTimestamp(2).toLocalDateTime())
                        .withCurrentWeather(weatherCodeClimateR.selectByID(rs.getInt(3)))
                        .withCurrentTemperature(rs.getInt(4))
                        .withAstronomy(astronomyClimateCRUD.selectByID(rs.getInt(5)))
                        .withAtmosphere(atmosphereClimateCRUD.selectByID(rs.getInt(6)))
                        .withWind(windClimateCRUD.selectByID(rs.getInt(7)))
                        .build();
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Forecast:");
            ex.printStackTrace();
        }
        return today;
    }

    @Override
    public List<Today> selectAll() {
        todays = new LinkedList<>();
        String select = "select * from forecast";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                today = TodayBuilder.builder()
                        .withDate(rs.getTimestamp(2).toLocalDateTime())
                        .withCurrentWeather(weatherCodeClimateR.selectByID(rs.getInt(3)))
                        .withCurrentTemperature(rs.getInt(4))
                        .withAstronomy(astronomyClimateCRUD.selectByID(rs.getInt(5)))
                        .withAtmosphere(atmosphereClimateCRUD.selectByID(rs.getInt(6)))
                        .withWind(windClimateCRUD.selectByID(rs.getInt(7)))
                        .build();
                todays.add(today);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Forecasts:");
            ex.printStackTrace();
        }
        return todays;
    }


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
    }
}
