package com.globant.bootcamp.persistence;

import com.globant.bootcamp.builder.WindBuilder;
import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.Wind;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
@Component
public class WindCRUD extends QueryExecuter implements ClimateCRUD<Wind> {


    public WindCRUD(Connection con) {
        super(con);
    }

    @Override
    public int insert(Wind wind) {
        int key = -1;
        String insert = " insert into wind (chill, direction, speed) values (?, ?, ?)";
        try {
            key=executeResult(insert, wind.getChill(), wind.getDirection(), wind.getSpeed());
        } catch (SQLException ex) {
            System.out.println("Error inserting Wind:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public boolean update(Wind wind) {
        boolean gotInserted = false;
        String update = " UPDATE wind set chill = ?, direction = ?, speed = ? where idwind = ?";
        try{

            executeUpdate(update, wind.getChill(), wind.getDirection(), wind.getSpeed(), wind.getId());
            gotInserted = true;
        } catch (SQLException ex) {
            System.out.println("Error updating Wind:");
            ex.printStackTrace();
        }

        return gotInserted;
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete wind where wind= ?";
        try {
            executeDelete(delete, id);
        } catch (SQLException e) {
            System.out.println("Error deleting Wind:");
            e.printStackTrace();
        }
    }

    @Override
    public Wind selectByID(int id) {
        String select = "select * from wind where idwind = ?";
        Wind wind = null;
        try {
            ResultSet rs = executeSelectByID(select, id);
            while (rs.next()) {
                wind = WindBuilder.builder()
                        .withID(rs.getInt(1))
                        .withChill(rs.getInt(2))
                        .withDirection(rs.getInt(3))
                        .withSpeed(rs.getInt(4))
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting one Wind:");
            e.printStackTrace();
        }
        return wind;
    }

    @Override
    public List<Wind> selectAll() {

        List<Wind> winds = new LinkedList<>();
        String select = "select * from wind";
        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                Wind wind = WindBuilder.builder()
                        .withID(rs.getInt(1))
                        .withChill(rs.getInt(2))
                        .withDirection(rs.getInt(3))
                        .withSpeed(rs.getInt(4))
                        .build();
                winds.add(wind);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting all Wind:");
            e.printStackTrace();
        }
        return winds;
    }

    public Wind selectByObject(Wind windInput) {
        String select = "select * from wind where chill = ? and direction = ? and speed = ?";
        Wind wind = null;
        try {
            ResultSet rs = executeSelectBySomething(select, windInput.getChill(), windInput.getDirection(),
                    windInput.getSpeed());
            while (rs.next()) {
                wind = WindBuilder.builder()
                        .withID(rs.getInt(1))
                        .withChill(rs.getInt(2))
                        .withDirection(rs.getInt(3))
                        .withSpeed(rs.getInt(4))
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error selecting by Wind:");
            e.printStackTrace();
        }
        return wind;
    }
}
