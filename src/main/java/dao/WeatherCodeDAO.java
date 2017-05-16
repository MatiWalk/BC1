package dao;

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
public class WeatherCodeDAO implements ClimateDAO<WeatherCode> {
    List<WeatherCode> weatherCodes;
    Connection con = DBConnector.getInstance().getCon();

    @Override
    public int insert(WeatherCode weatherCode) {
        return -1;
    }

    @Override
    public void update(WeatherCode weatherCode, int id) {
    }

    @Override
    public void deleteByID(int id) {
    }

    @Override
    public WeatherCode selectByID(int i) {
        return null;
    }

    public List<WeatherCode> selectAll() {
        weatherCodes = new LinkedList<>();
        String sql = "select * from weather_code";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                WeatherCode wc = new WeatherCode.Builder()
                        .withCode(rs.getInt(1))
                        .withString(rs.getString(2))
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
