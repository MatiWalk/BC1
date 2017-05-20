package com.globant.bootcamp.connection;

import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * Created by Mati on 11/05/2017.
 */
@Component
public class DBConnector {

    private Connection con;
    private static DBConnector instance;
    private String driver/* = "com.mysql.cj.jdbc.Driver"*/;
    private String username /*= "root"*/;
    private String password/* = "admin"*/;
    private String url /*= "jdbc:mysql://localhost:3306/weather?useSSL=false"*/;

    public DBConnector() {

        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {

            System.out.println("Error trying to open connection" );
            e.printStackTrace();

        }
    }

    public DBConnector(String driver, String username, String password, String url) {

        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {

            System.out.println("Error trying to open connection" );
            e.printStackTrace();

        }
    }



    public static DBConnector getInstance(){
        if(instance == null) {
            instance = new DBConnector();
        }
        return instance;
        //return null;
    }

    //used for testing
    public void setTestStrings(String dr, String un, String pw, String u){
        driver = dr;
        username = un;
        password = pw;
        url = u;
    }

    public Connection getCon() {
        return con;
    }


}
