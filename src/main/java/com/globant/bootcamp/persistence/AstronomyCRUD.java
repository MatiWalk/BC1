package com.globant.bootcamp.persistence;

import com.globant.bootcamp.model.Astronomy;
import com.globant.bootcamp.builder.AstronomyBuilder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mati on 16/05/2017.
 */

@Component
public class AstronomyCRUD extends QueryExecuter implements ClimateCRUD<Astronomy> {

    private List<Astronomy> astronomies;
    private Astronomy astronomy;

    public AstronomyCRUD(Connection con) {
        super(con);
    }

    @Override
    public int insert(Astronomy astronomy) {
        int key = -1;
        String insert = " insert into astronomy (sunrise, sunset) values (?, ?)";
        /*try {
            PreparedStatement ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setTime(1, Time.valueOf(astronomy.getSunRise()));
            ps.setTime(2,Time.valueOf(astronomy.getSunSet()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error inserting Astronomy:");
            ex.printStackTrace();
        }*/


        try {
            key=executeResult(insert, astronomy.getSunRise(), astronomy.getSunSet());
        } catch (SQLException ex) {
            System.out.println("Error inserting Astronomy:");
            ex.printStackTrace();
        }
        return key;
    }

    @Override
    public void update(Astronomy astronomy, int id) {
        String update = " UPDATE astronomy set sunrise = ?, sunset = ? where idastronomy = ?";
        /*try {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setTime(1, Time.valueOf(astronomy.getSunRise()));
            ps.setTime(2,Time.valueOf(astronomy.getSunSet()));
            ps.setInt(3, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error updating Astronomy:");
            ex.printStackTrace();
        }*/
        try{
        executeUpdate(update, astronomy.getSunRise(), astronomy.getSunSet(), id);
        } catch (SQLException ex) {
        System.out.println("Error updating Astronomy:");
        ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete astronomy where idastronomy= ?";
        /*try {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error deleting Astronomy:");
            ex.printStackTrace();
        }*/

        try {
            executeDelete(delete, id);
        } catch (SQLException e) {
            System.out.println("Error deleting Astronomy:");
            e.printStackTrace();
        }
    }

    @Override
    public Astronomy selectByID(int id) {
        String select = "select * from astronomy where idastronomy = ?";
        /*try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                astronomy = AstronomyBuilder.builder()
                        .withSunrise(rs.getTime(2).toLocalTime())
                        .withSunset(rs.getTime(3).toLocalTime())
                        .build();
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Astronomy:");
            ex.printStackTrace();
        }*/
        try {
            ResultSet rs = executeSelectByID(select, id);
            while (rs.next()) {
                astronomy = AstronomyBuilder.builder()
                        .withSunrise(rs.getTime(2).toLocalTime())
                        .withSunset(rs.getTime(3).toLocalTime())
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting one Astronomy:");
            e.printStackTrace();
        }

        return astronomy;
    }

    @Override
    public List<Astronomy> selectAll() {

        astronomies = new LinkedList<>();
        String select = "select * from astronomy";
        /*try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                astronomy = AstronomyBuilder.builder()
                        .withSunrise(rs.getTime(2).toLocalTime())
                        .withSunset(rs.getTime(3).toLocalTime())
                        .build();
                astronomies.add(astronomy);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Astronomies:");
            ex.printStackTrace();
        }*/
        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                astronomy = AstronomyBuilder.builder()
                        .withSunrise(rs.getTime(2).toLocalTime())
                        .withSunset(rs.getTime(3).toLocalTime())
                        .build();
                astronomies.add(astronomy);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting all Astronomy:");
            e.printStackTrace();
        }

        return astronomies;
    }
}
