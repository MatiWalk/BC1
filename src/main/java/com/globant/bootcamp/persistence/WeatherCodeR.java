package com.globant.bootcamp.persistence;

import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.WeatherCode;
import com.globant.bootcamp.builder.WeatherCodeBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class WeatherCodeR implements ClimateR {
    private List<WeatherCode> weatherCodes;
    private Connection con = DBConnector.getInstance().getCon();

    public WeatherCode selectByID(int id) {
        WeatherCode wc = new WeatherCode();
        String select = "select * from weathercode where idweathercode = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                wc = WeatherCodeBuilder.builder()
                        .withCode(rs.getInt(1))
                        .withWeather(rs.getString(2))
                        .build();
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Weather Code:");
            ex.printStackTrace();
        }
        return wc;
    }

    public List<WeatherCode> selectAll() {
        weatherCodes = new LinkedList<>();
        String sql = "select * from weathercode";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WeatherCode wc = WeatherCodeBuilder.builder()
                        .withCode(rs.getInt(1))
                        .withWeather(rs.getString(2))
                        .build();
                weatherCodes.add(wc);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error loading weather codes:");
            ex.printStackTrace();
        }
        return weatherCodes;
    }
}
