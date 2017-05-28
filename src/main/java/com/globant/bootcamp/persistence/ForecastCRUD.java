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

    private ClimateR<WeatherCode> weatherCodeClimateR;

    public ForecastCRUD(WeatherCodeR weatherCodeClimateR, Connection con) {
        super(con);
        this.weatherCodeClimateR = weatherCodeClimateR;
    }

    @Override
    public int insert(Forecast forecast) {
        int key = -1;
        String insert = " insert into forecast (woeid, idforecastweather, date, hightemperature, lowtemperature) values (?, ?, ?, ?, ?)";
        try {
            key=executeResult(insert, forecast.getWoeid(), forecast.getForecastWeather().getCode(), forecast.getDate(), forecast.getHighTemperature(),
                    forecast.getLowTemperature());
        } catch (SQLException ex) {
            System.out.println("Error inserting Forecast:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Forecast forecast) {
        String update = " UPDATE forecast set idforecastweather = ?, hightemperature = ?, " +
                "lowtemperature = ? where date = ? and woeid = ?";
        try{
            executeUpdate(update, forecast.getForecastWeather().getCode(), forecast.getHighTemperature(), forecast.getLowTemperature(),
                    forecast.getDate(), forecast.getWoeid());
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
        Forecast forecast = null;
        String select = "select * from forecast where idforecast = ?";
        try {
            ResultSet rs = executeSelectByID(select, id);
            while (rs.next()) {
                forecast = ForecastBuilder.builder()
                        .withID(rs.getInt(1))
                        .withWOEID(rs.getInt(2))
                        .withDate(rs.getDate(3).toLocalDate())
                        .withForecastWeather(weatherCodeClimateR.selectByID(rs.getInt(4)))
                        .withHighTemperature(rs.getInt(5))
                        .withLowTemperature(rs.getInt(6))
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
        List<Forecast> forecasts = new LinkedList<>();
        String select = "select * from forecast";
        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                Forecast forecast = ForecastBuilder.builder()
                        .withID(rs.getInt(1))
                        .withWOEID(rs.getInt(2))
                        .withDate(rs.getDate(3).toLocalDate())
                        .withForecastWeather(weatherCodeClimateR.selectByID(rs.getInt(4)))
                        .withHighTemperature(rs.getInt(5))
                        .withLowTemperature(rs.getInt(6))
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

    @Override
    public Forecast selectByObject(Forecast forecastInput) {
        String select = "select * from forecast where woeid = ? and date = ?";
        Forecast forecast = null;
        try {
            ResultSet rs = executeSelectByID(select, forecastInput.getWoeid(), forecastInput.getDate());
            while (rs.next()) {
                forecast = ForecastBuilder.builder()
                        .withID(rs.getInt(1))
                        .withWOEID(rs.getInt(2))
                        .withDate(rs.getDate(3).toLocalDate())
                        .withForecastWeather(weatherCodeClimateR.selectByID(rs.getInt(4)))
                        .withHighTemperature(rs.getInt(5))
                        .withLowTemperature(rs.getInt(6))
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting by Forecast:");
            e.printStackTrace();
        }
        return forecast;
    }


}
