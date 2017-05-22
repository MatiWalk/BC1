package com.globant.bootcamp.persistence;

import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.Location;
import com.globant.bootcamp.builder.LocationBuilder;
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

    private List<Location> locations;
    private Location location;
    private Connection con;

    public LocationCRUD(Connection con) {
        super(con);
    }

    @Override
    public int insert(Location location) {
        int key = -1;
        String insert = " insert into Location (country, zone, city) values (?, ?, ?)";
        try {
            key=executeResult(insert, location.getCountry(), location.getZone(), location.getCity());
        } catch (SQLException ex) {
            System.out.println("Error inserting Location:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Location location) {
        String update = " UPDATE location set Country = ?, Zone = ?, City = ? where idlocation = ?";
        try{
            executeUpdate(update, location.getCountry(), location.getZone(), location.getCity(), location.getId());
        } catch (SQLException ex) {
            System.out.println("Error updating Location:");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete location where idlocation = ?";
        try {
            executeDelete(delete, id);
        } catch (SQLException e) {
            System.out.println("Error deleting Location:");
            e.printStackTrace();
        }
    }

    @Override
    public Location selectByID(int id) {

        String select = "select * from location where idlocation = ?";
        try {
            ResultSet rs = executeSelectByID(select, id);
            while (rs.next()) {
                location = LocationBuilder.builder()
                        .withID(rs.getInt(1))
                        .withCountry(rs.getString(2))
                        .withZone(rs.getString(3))
                        .withCity(rs.getString(4))
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

        locations = new LinkedList<>();
        String select = "select * from location";
        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                location = LocationBuilder.builder()
                        .withID(rs.getInt(1))
                        .withCountry(rs.getString(2))
                        .withZone(rs.getString(3))
                        .withCity(rs.getString(4))
                        .build();
                locations.add(location);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting all Location:");
            e.printStackTrace();
        }
        return locations;
    }
}
