package com.globant.bootcamp.persistence;

import java.sql.*;
import com.globant.bootcamp.model.barometricPressure;
import com.mysql.cj.api.mysqla.result.Resultset;

/**
 * Created by Mati on 20/05/2017.
 */
abstract class QueryExecuter<T> {

    protected Connection con;

    public QueryExecuter(Connection con) {
        this.con = con;
    }

    protected int executeResult(String sql, Object... values) throws SQLException {
        int key = -1;
        PreparedStatement ps = setValues(sql, values);
        ps.executeUpdate();
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            key = generatedKeys.getInt(1);
        }
        generatedKeys.close();
        return key;
    }

    protected void executeUpdate(String sql, Object... values) throws SQLException {
        PreparedStatement ps = setValues(sql, values);
        ps.executeUpdate();
    }

    protected void executeDelete(String sql, Object... values) throws SQLException {
        PreparedStatement ps = setValues(sql, values);
        ps.executeUpdate();

    }

    protected ResultSet executeSelectByID(String sql, Object... values) throws SQLException {
        PreparedStatement ps = setValues(sql, values);
        return ps.executeQuery();
    }

    protected ResultSet executeSelectAll(String sql) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }

    protected PreparedStatement setValues(String sql, Object... values) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Enum){
                barometricPressure bp = (barometricPressure)values[i];
                ps.setObject(i + 1, bp.ordinal());
            }else {
                ps.setObject(i + 1, values[i]);
            }
        }
        return ps;
    }

    protected ResultSet executeSelectBySomething(String sql, Object... values) throws SQLException {
        PreparedStatement ps = setValues(sql, values);
        return ps.executeQuery();
    }

}
