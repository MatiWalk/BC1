package dao;

import builder.TodayBuilder;
import builder.WindBuilder;
import controller.DBConnector;
import model.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class TodayDAO implements ClimateDAO<Today> {

    private List<Today> todays;
    private Today today;
    private Connection con = DBConnector.getInstance().getCon();
    private ClimateDAO<Astronomy> astronomyClimateDAO = new AstronomyDAO();
    private ClimateDAO<Atmosphere> atmosphereClimateDAO = new AtmosphereDAO();
    private ClimateDAO<Wind> windClimateDAO = new WindDAO();
    private WeatherCodeDAO weatherCodeClimateDAO = new WeatherCodeDAO();


    @Override
    public int insert(Today today) {
        int key = -1;
        String insert = "insert into today (idforecastweather, date, hightemperature, lowtemperature, idcurrentweather, " +
                "currenttemperature, idastronomy, idatmosphere, idwind) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, today.getForecastWeather().getCode());
            ps.setDate(2, Date.valueOf(today.getDate()));
            ps.setInt(3, today.getHighTemperature());
            ps.setInt(4, today.getLowTemperature());
            ps.setInt(5, today.getCurrentWeather().getCode());
            ps.setInt(6, today.getCurrentTemperature());
            ps.setInt(7, astronomyClimateDAO.insert(today.getAstronomy()));
            ps.setInt(8, atmosphereClimateDAO.insert(today.getAtmosphere()));
            ps.setInt(9, windClimateDAO.insert(today.getWind()));
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
        astronomyClimateDAO.update(today.getAstronomy(), fk[0]);
        atmosphereClimateDAO.update(today.getAtmosphere(), fk[1]);
        windClimateDAO.update(today.getWind(), fk[2]);

        String update = " UPDATE today set idforecastweather = ?, date = ?, hightemperature = ?, " +
                "lowtemperature = ?, idcurrentweather = ?, currenttemperature = ?, " +
                "idastronomy = ?, idatmosphere = ?, idwind = ? where idtoday = ?";
        try {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setInt(1, today.getForecastWeather().getCode());
            ps.setDate(2, Date.valueOf(today.getDate()));
            ps.setInt(3, today.getHighTemperature());
            ps.setInt(4, today.getLowTemperature());
            ps.setInt(5, today.getCurrentWeather().getCode());
            ps.setInt(6, today.getCurrentTemperature());
            ps.setInt(7, fk[0]);
            ps.setInt(8, fk[1]);
            ps.setInt(9, fk[2]);
            ps.setInt(10, id);

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
    public Today selectByID(int id) {/*
        String select = "select * from today t where idtoday = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(select);
            while (rs.next()) {

                today = TodayBuilder.builder()
                        .withForecastWeather()
                        .withDate()
                        .withHighTemperature()
                        .withLowTemperature()
                        .
                        .withCurrentTemperature(rs.getInt(2))
                        .withAstronomy()
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Forecast:");
            ex.printStackTrace();
        }*/
        return today;
    }

    @Override
    public List<Today> selectAll() {
        return null;
    }

    private int[] getForeignKeys(int id){
        int[] fk = new int[3];
        String select = "select idastronomy, idatmosphere, idwind from today where idtoday = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(select);
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
