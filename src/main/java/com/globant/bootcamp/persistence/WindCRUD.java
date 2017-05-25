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


    private Connection con;
    Wind wind;
    List<Wind> winds;

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
    public void update(Wind wind) {
        String update = " UPDATE wind set chill = ?, direction = ?, speed = ? where idwind = ?";
        try{
            executeUpdate(update, wind.getChill(), wind.getDirection(), wind.getSpeed(), wind.getId());
        } catch (SQLException ex) {
            System.out.println("Error updating Wind:");
            ex.printStackTrace();
        }
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

        winds = new LinkedList<>();
        String select = "select * from wind";
        try {
            ResultSet rs = executeSelectAll(select);
            while (rs.next()) {
                wind = WindBuilder.builder()
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

    public Wind selectByObject(Wind wind) {
        String select = "select * from wind where chill = ? and direction = ? and speed = ?";

        try {
            ResultSet rs = executeSelectBySomething(select, wind.getChill(), wind.getDirection(),
                    wind.getSpeed());
            while (rs.next()) {
                this.wind = WindBuilder.builder()
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
        return this.wind;
    }
}
