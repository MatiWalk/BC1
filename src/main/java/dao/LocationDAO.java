package dao;

import controller.DBConnector;
import model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class LocationDAO implements ClimateDAO<Location> {

    List<Location> locations;
    Location location;
    Connection con = DBConnector.getInstance().getCon();


    @Override
    public int insert(Location location) {
        int key = -1;
        String insert = " insert into Location (country, zone, city) values (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, forecast.getForecastWeather().getCode());
            ps.setDate(2, Date.valueOf(forecast.getDate()));
            ps.setInt(3, forecast.getHighTemperature());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error inserting Location:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Location location, int id) {

    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public Location selectByID(int id) {
        return null;
    }

    @Override
    public List<Location> selectAll() {
        return null;
    }
}
