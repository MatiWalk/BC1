package dao;

import builder.WeatherCodeBuilder;
import controller.DBConnector;
import model.WeatherCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class WeatherCodeDAO {
    private List<WeatherCode> weatherCodes;
    private Connection con = DBConnector.getInstance().getCon();



    public List<WeatherCode> selectAll() {
        weatherCodes = new LinkedList<>();
        String sql = "select * from weather_code";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
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
