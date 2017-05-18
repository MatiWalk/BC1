package com.globant.bootcamp.persistence;

import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.Atmosphere;
import com.globant.bootcamp.builder.AtmosphereBuilder;
import com.globant.bootcamp.model.barometricPressure;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class AtmosphereCRUD implements ClimateCRUD<Atmosphere> {

    private List<Atmosphere> atmospheres;
    private Atmosphere atmosphere;
    private Connection con = DBConnector.getInstance().getCon();

    @Override
    public int insert(Atmosphere atmosphere) {
        int key = -1;
        String insert = " insert into atmopsphere (humidity, pressure, rising, visibility) values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, atmosphere.getHumidity());
            ps.setFloat(2, atmosphere.getPressure());
            ps.setInt(3, atmosphere.getRising().ordinal());
            ps.setFloat(4, atmosphere.getVisibility());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error inserting Atmosphere:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Atmosphere atmosphere, int id) {
        String update = " UPDATE atmosphere set humidity = ?, pressure = ?, rising = ?, visibility = ? where idatmosphere = ?";
        try {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setInt(1, atmosphere.getHumidity());
            ps.setFloat(2, atmosphere.getPressure());
            ps.setInt(3, atmosphere.getRising().ordinal());
            ps.setFloat(4, atmosphere.getVisibility());
            ps.setInt(5, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error updating Atmosphere:");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete atmosphere where idatmosphere = ?";
        try {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error deleting Atmosphere:");
            ex.printStackTrace();
        }
    }

    @Override
    public Atmosphere selectByID(int id) {
        String select = "select * from atmosphere where idatmosphere = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(select);
            while (rs.next()) {
                atmosphere = AtmosphereBuilder.builder()
                    .withHumidity(rs.getInt(2))
                    .withPressure(rs.getFloat(3))
                    .withRising(barometricPressure.values()[rs.getInt(4)])
                    .withVisibility(rs.getFloat(5))
                    .build();
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Atmosphere:");
            ex.printStackTrace();
        }
        return atmosphere;
    }

    @Override
    public List<Atmosphere> selectAll() {
        atmospheres = new LinkedList<>();
        String select = "select * from atmosphere";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery(select);
            while (rs.next()) {
                atmosphere = AtmosphereBuilder.builder()
                        .withHumidity(rs.getInt(2))
                        .withPressure(rs.getFloat(3))
                        .withRising(barometricPressure.values()[rs.getInt(4)])
                        .withVisibility(rs.getFloat(5))
                        .build();
                atmospheres.add(atmosphere);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Atmospheres:");
            ex.printStackTrace();
        }
        return atmospheres;
    }
}
