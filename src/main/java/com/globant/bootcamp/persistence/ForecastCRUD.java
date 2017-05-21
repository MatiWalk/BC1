package com.globant.bootcamp.persistence;

import com.globant.bootcamp.builder.ForecastBuilder;
import com.globant.bootcamp.model.Forecast;
import com.globant.bootcamp.model.WeatherCode;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
@Component
public class ForecastCRUD extends QueryExecuter implements ClimateCRUD<Forecast> {

    private List<Forecast> forecasts;
    private Forecast forecast;
    private int parentkey;
    private ClimateR<WeatherCode> weatherCodeClimateR;

    public ForecastCRUD(WeatherCodeR weatherCodeClimateR, Connection con) {
        super(con);
        this.weatherCodeClimateR = weatherCodeClimateR;
    }

    @Override
    public int insert(Forecast forecast) {
        int key = -1;
        setParentkey();
        String insert = " insert into forecast (idresult, idforecastweather, date, hightemperature, lowtemperature) values (?, ?, ?, ?, ?)";
        try {
            key=executeResult(insert, parentkey, forecast.getForecastWeather().getCode(), forecast.getDate(), forecast.getHighTemperature(),
                    forecast.getLowTemperature());
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
        try{
            executeUpdate(update, parentkey, forecast.getForecastWeather().getCode(), forecast.getDate(), forecast.getHighTemperature(),
                    forecast.getLowTemperature(), id);
        } catch (SQLException ex) {
            System.out.println("Error updating Forecast:");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete forecast where idforecast = ?";
        try {
            executeDelete(delete, id);
        } catch (SQLException e) {
            System.out.println("Error deleting Forecast:");
            e.printStackTrace();
        }

    }

    @Override
    public Forecast selectByID(int id) {

        String select = "select * from forecast where idforecast = ?";
        try {
            ResultSet rs = executeSelectByID(select, id);
            while (rs.next()) {
                forecast = ForecastBuilder.builder()
                        .withDate(rs.getDate(2).toLocalDate())
                        .withForecastWeather(weatherCodeClimateR.selectByID(rs.getInt(3)))
                        .withHighTemperature(rs.getInt(4))
                        .withLowTemperature(rs.getInt(5))
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting one Forecast:");
            e.printStackTrace();
        }
        return forecast;
    }

    @Override
    public List<Forecast> selectAll() {
        forecasts = new LinkedList<>();
        String select = "select * from forecast";
        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                forecast = ForecastBuilder.builder()
                        .withDate(rs.getDate(2).toLocalDate())
                        .withForecastWeather(weatherCodeClimateR.selectByID(rs.getInt(3)))
                        .withHighTemperature(rs.getInt(4))
                        .withLowTemperature(rs.getInt(5))
                        .build();
                forecasts.add(forecast);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting all Forecast:");
            e.printStackTrace();
        }
        return forecasts;
    }

    private void setParentkey(){
        String query = "select idresult from result order by idresult desc limit 1";
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
