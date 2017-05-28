package com.globant.bootcamp.persistence;

import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.Forecast;
import com.globant.bootcamp.model.Location;
import com.globant.bootcamp.builder.LocationBuilder;
import com.globant.bootcamp.model.Today;
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
public class LocationCRUD extends QueryExecuter implements ClimateCRUD<Location> {

    private ClimateCRUD<Today> todayClimateCRUD;
    private ClimateCRUD<Forecast> forecastClimateCRUD;

    public LocationCRUD(Connection con, ClimateCRUD<Today> todayClimateCRUD, ClimateCRUD<Forecast> forecastClimateCRUD) {
        super(con);
        this.todayClimateCRUD = todayClimateCRUD;
        this.forecastClimateCRUD = forecastClimateCRUD;
    }

    @Override
    public int insert(Location location) {
        int key = -1;

        String insert = " insert into Location (woeid, country, zone, city, lastupdate) values (?, ?, ?, ?, ?)";
        try {
            key=executeResult(insert, location.getWoeid(), location.getCountry(), location.getZone(), location.getCity(), location.getLastUpdate());
        } catch (SQLException ex) {
            System.out.println("Error inserting Location:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Location location) {
        String update = " UPDATE location set lastupdate = ? where Country = ? and Zone = ? and City = ?";

        try{
            executeUpdate(update, location.getLastUpdate(), location.getCountry(), location.getZone(),
                location.getCity());
        } catch (SQLException ex) {
            System.out.println("Error updating Location:");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = "delete location where woeid = ?";
        try {
            executeDelete(delete, id);
        } catch (SQLException e) {
            System.out.println("Error deleting Location:");
            e.printStackTrace();
        }
    }

    @Override
    public Location selectByID(int id) {
        Location location = null;
        String select = "select * from location where woeid = ?";
        try {
            ResultSet rs = executeSelectByID(select, id);
            while (rs.next()) {
                location = LocationBuilder.builder()
                        .withWoeid(rs.getInt(1))
                        .withCountry(rs.getString(2))
                        .withZone(rs.getString(3))
                        .withCity(rs.getString(4))
                        .withLastUpdate(rs.getTimestamp(5).toLocalDateTime())
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting one Location:");
            e.printStackTrace();
        }
        return location;
    }

    @Override
    public List<Location> selectAll() {
        List<Location> locations = new LinkedList<>();
        String select = "select * from location";
        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                Location location = LocationBuilder.builder()
                        .withWoeid(rs.getInt(1))
                        .withCountry(rs.getString(2))
                        .withZone(rs.getString(3))
                        .withCity(rs.getString(4))
                        .withLastUpdate(rs.getTimestamp(5).toLocalDateTime())
                        .build();
                locations.add(location);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting all Locations:");
            e.printStackTrace();
        }
        return locations;
    }

    @Override
    public Location selectByObject(Location locationInput) {
        String select = "select * from location where country = ? and zone = ? and city = ?";
        Location location = null;
        try {
            ResultSet rs = executeSelectByID(select, locationInput.getCountry(), locationInput.getZone(), locationInput.getCity());
            while (rs.next()) {
                location = LocationBuilder.builder()
                        .withWoeid(rs.getInt(1))
                        .withCountry(rs.getString(2))
                        .withZone(rs.getString(3))
                        .withCity(rs.getString(4))
                        .withLastUpdate(rs.getTimestamp(5).toLocalDateTime())
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting by Location:");
            e.printStackTrace();
        }
        return location;
    }
}
