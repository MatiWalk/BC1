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
public class LocationCRUD implements ClimateCRUD<Location> {

    private List<Location> locations;
    private Location location;
    private Connection con;

    public LocationCRUD(Connection con) {
        this.con = con;
    }

    @Override
    public int insert(Location location) {
        int key = -1;
        String insert = " insert into Location (country, zone, city) values (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, location.getCountry());
            ps.setString(2, location.getZone());
            ps.setString(3, location.getCity());
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
        String update = " UPDATE location set Country = ?, Zone = ?, City = ? where idlocation = ?";
        try {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, location.getCountry());
            ps.setString(2, location.getZone());
            ps.setString(3, location.getCity());
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error updating Location:");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete location where idlocation = ?";
        try {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error deleting Location:");
            ex.printStackTrace();
        }
    }

    @Override
    public Location selectByID(int id) {

        String select = "select * from location where idlocation = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                location = LocationBuilder.builder()
                    .withCountry(rs.getString(2))
                    .withZone(rs.getString(3))
                    .withCity(rs.getString(4))
                    .build();
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Location:");
            ex.printStackTrace();
        }
        return location;
    }

    @Override
    public List<Location> selectAll() {

        locations = new LinkedList<>();
        String select = "select * from location";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                location = LocationBuilder.builder()
                        .withCountry(rs.getString(2))
                        .withZone(rs.getString(3))
                        .withCity(rs.getString(4))
                        .build();
                locations.add(location);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Locations:");
            ex.printStackTrace();
        }
        return locations;
    }
}
