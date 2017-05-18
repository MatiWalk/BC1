package com.globant.bootcamp.persistence;

import com.globant.bootcamp.builder.WindBuilder;
import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.Wind;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class WindCRUD implements ClimateCRUD<Wind> {


    private Connection con = DBConnector.getInstance().getCon();

    @Override
    public int insert(Wind wind) {
        int key = -1;
        String insert = " insert into wind (chill, direction, speed) values (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, wind.getChill());
            ps.setInt(2, wind.getDirection());
            ps.setInt(3, wind.getSpeed());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error inserting wind:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Wind wind, int id) {
        String update = " UPDATE wind set chill = ?, direction = ?, speed where idweather = ?";
        try {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setInt(1, wind.getChill());
            ps.setInt(2, wind.getDirection());
            ps.setInt(3, wind.getSpeed());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error updating wind:");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete wind where idweather= ?";
        try {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error deleting Wind:");
            ex.printStackTrace();
        }
    }

    @Override
    public Wind selectByID(int id) {
        Wind wind = null;
        String select = "select * from wind where idwind = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(select);
            while (rs.next()) {
                wind = WindBuilder.builder()
                        .withChill(rs.getInt(2))
                        .withDirection(rs.getInt(3))
                        .withSpeed(rs.getInt(4))
                        .build();
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Astronomy:");
            ex.printStackTrace();
        }
        return wind;
    }

    @Override
    public List<Wind> selectAll() {
        Wind wind = null;
        List<Wind> winds = new LinkedList<>();
        String select = "select * from astronomy";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery(select);
            while (rs.next()) {
                wind = WindBuilder.builder()
                        .withChill(rs.getInt(2))
                        .withDirection(rs.getInt(3))
                        .withSpeed(rs.getInt(4))
                        .build();
                winds.add(wind);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Astronomies:");
            ex.printStackTrace();
        }
        return winds;
    }
}
