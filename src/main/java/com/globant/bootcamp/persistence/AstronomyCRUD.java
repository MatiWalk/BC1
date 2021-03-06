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
public class AstronomyCRUD extends QueryExecuter<Astronomy> implements ClimateCRUD<Astronomy> {

    public AstronomyCRUD(Connection con) {
        super(con);
    }

    @Override
    public int insert(Astronomy astronomy) {
        int key = -1;
        String insert = " insert into astronomy (sunrise, sunset) values (?, ?)";



        try {
            key=executeResult(insert, astronomy.getSunRise(), astronomy.getSunSet());
        } catch (SQLException ex) {
            System.out.println("Error inserting Astronomy:");
            ex.printStackTrace();
        }
        return key;
    }

    @Override
    public boolean update(Astronomy astronomy) {
        boolean gotInserted = false;
        String update = " UPDATE astronomy set sunrise = ?, sunset = ? where idastronomy = ?";
        try{
        executeUpdate(update, astronomy.getSunRise(), astronomy.getSunSet(), astronomy.getId());
        gotInserted = true;
        } catch (SQLException ex) {
        System.out.println("Error updating Astronomy:");
        ex.printStackTrace();
        }
         return gotInserted;
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete astronomy where idastronomy= ?";

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
        Astronomy astronomy = null;
        try {
            ResultSet rs = executeSelectByID(select, id);
            while (rs.next()) {
                astronomy = AstronomyBuilder.builder()
                        .withID(rs.getInt(1))
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
        List<Astronomy> astronomies = new LinkedList<>();
        String select = "select * from astronomy";
        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                Astronomy astronomy = AstronomyBuilder.builder()
                        .withID(rs.getInt(1))
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

    public Astronomy selectByObject(Astronomy astronomyInput) {
        String select = "select * from astronomy where sunrise = ? and sunset = ?";
        Astronomy astronomy = null;
        try {
            ResultSet rs = executeSelectBySomething(select, astronomyInput.getSunRise(), astronomyInput.getSunSet());
            while (rs.next()) {
                astronomy = AstronomyBuilder.builder()
                        .withID(rs.getInt(1))
                        .withSunrise(rs.getTime(2).toLocalTime())
                        .withSunset(rs.getTime(3).toLocalTime())
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting by Astronomy:");
            e.printStackTrace();
        }
        return astronomy;
    }

}
