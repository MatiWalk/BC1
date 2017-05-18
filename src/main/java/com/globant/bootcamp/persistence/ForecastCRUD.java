package com.globant.bootcamp.persistence;

import com.globant.bootcamp.builder.ForecastBuilder;
import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.Forecast;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class ForecastCRUD implements ClimateCRUD<Forecast> {

    private List<Forecast> forecasts;
    private Forecast forecast;
    private int parentkey;
    private WeatherCodeR weatherCodeDAO = new WeatherCodeR();
    private Connection con = DBConnector.getInstance().getCon();

    @Override
    public int insert(Forecast forecast) {
        int key = -1;
        setParentkey();
        String insert = " insert into forecast (idresult, idforecastweather, date, hightemperature, lowtemperature) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, parentkey);
            ps.setInt(2, forecast.getForecastWeather().getCode());
            ps.setDate(3, Date.valueOf(forecast.getDate()));
            ps.setInt(4, forecast.getHighTemperature());
            ps.setInt(5, forecast.getLowTemperature());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error inserting Forecast:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Forecast forecast, int id) {
        String update = " UPDATE forecast set idresult = ?, idforecastweather = ?, date = ?, hightemperature = ?, " +
                "lowtemperature = ? where idforecast = ?";
        try {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setInt(1, parentkey);
            ps.setInt(2, forecast.getForecastWeather().getCode());
            ps.setDate(3, Date.valueOf(forecast.getDate()));
            ps.setInt(4, forecast.getHighTemperature());
            ps.setInt(5, forecast.getLowTemperature());
            ps.setInt(6, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error updating Forecast:");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete forecast where idforecast = ?";
        try {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error deleting Forecast:");
            ex.printStackTrace();
        }

    }

    @Override
    public Forecast selectByID(int id) {

        String select = "select * from forecast where idforecast = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(select);
            while (rs.next()) {
                forecast = ForecastBuilder.builder()
                    .withForecastWeather(weatherCodeDAO.selectByID(rs.getInt(2)))
                    .withDate(rs.getDate(3).toLocalDate())
                    .withHighTemperature(rs.getInt(4))
                    .withLowTemperature(rs.getInt(5))
                    .build();
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Forecast:");
            ex.printStackTrace();
        }
        return forecast;
    }

    @Override
    public List<Forecast> selectAll() {
        forecasts = new LinkedList<>();
        String select = "select * from forecast";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery(select);
            while (rs.next()) {
                forecast = ForecastBuilder.builder()
                        .withForecastWeather(weatherCodeDAO.selectByID(rs.getInt(2)))
                        .withDate(rs.getDate(3).toLocalDate())
                        .withHighTemperature(rs.getInt(4))
                        .withLowTemperature(rs.getInt(5))
                        .build();
                forecasts.add(forecast);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Forecasts:");
            ex.printStackTrace();
        }
        return forecasts;
    }

    private void setParentkey(){
        String query = "select idresult from result order by id desc limit 1";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                parentkey = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
