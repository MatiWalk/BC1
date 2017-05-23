package com.globant.bootcamp.connection;

import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * Created by Mati on 11/05/2017.
 */
@Component
public class DBConnector {

    private Connection con;
    private String driver/* = "com.mysql.cj.jdbc.Driver"*/;
    private String username /*= "root"*/;
    private String password/* = "admin"*/;
    private String url /*= "jdbc:mysql://localhost:3306/weather?useSSL=false"*/;

    public DBConnector() {

        openConnection();
    }

    public DBConnector(String driver, String username, String password, String url) {
        this.driver = driver;
        this.username = username;
        this.password = password;
        this.url = url;
        openConnection();

    }

    private void openConnection(){
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {

            System.out.println("Error trying to open connection" );
            e.printStackTrace();

        }
    }

    public Connection getCon() {
        return con;
    }


}
