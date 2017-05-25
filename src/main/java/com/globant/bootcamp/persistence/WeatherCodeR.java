package com.globant.bootcamp.persistence;

import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.WeatherCode;
import com.globant.bootcamp.builder.WeatherCodeBuilder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
@Component
public class WeatherCodeR extends QueryExecuter implements ClimateR<WeatherCode> {
    private List<WeatherCode> weatherCodes;

    public WeatherCodeR(Connection con) {
        super(con);
    }

    public WeatherCode selectByID(int id) {
        WeatherCode wc = new WeatherCode();
        String select = "select * from weathercode where idweathercode = ?";
        try {
            ResultSet rs = executeSelectByID(select, id);
            while (rs.next()) {
                wc = WeatherCodeBuilder.builder()
                        .withCode(rs.getInt(1))
                        .withWeather(rs.getString(2))
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting one WeatherCode:");
            e.printStackTrace();
        }
        return wc;
    }

    public List<WeatherCode> selectAll() {
        weatherCodes = new LinkedList<>();
        String select = "select * from weathercode";

        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                WeatherCode wc = WeatherCodeBuilder.builder()
                        .withCode(rs.getInt(1))
                        .withWeather(rs.getString(2))
                        .build();
                weatherCodes.add(wc);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting all Weather Code:");
            e.printStackTrace();
        }
        return weatherCodes;
    }

    @Override
    public WeatherCode selectByObject(WeatherCode weatherCode) {
        WeatherCode wc = new WeatherCode();
        String select = "select * from weathercode where idweathercode = ? and weather = ?";
        try {
            ResultSet rs = executeSelectByID(select, weatherCode.getCode(), weatherCode.getWeather());
            while (rs.next()) {
                wc = WeatherCodeBuilder.builder()
                        .withCode(rs.getInt(1))
                        .withWeather(rs.getString(2))
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting one WeatherCode:");
            e.printStackTrace();
        }
        return wc;
    }


}
