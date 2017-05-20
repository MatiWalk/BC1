package com.globant.bootcamp.persistence;

import java.sql.*;
import java.util.List;

/**
 * Created by Mati on 20/05/2017.
 */
abstract class queryExecuter<T> {

    protected Connection con;

    public queryExecuter(Connection con) {
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

    protected void executeDelete(PreparedStatement ps){

    }

    protected T executeSelectBID(PreparedStatement ps){
        return null;
    }

    protected List<T> executeSelectAll(){
        return null;
    }

    protected PreparedStatement setValues(String sql, Object... values) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]);
        }
        return ps;
    }


}
