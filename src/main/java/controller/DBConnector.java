package controller;

import model.*;
import model.unit.Temperature;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by Mati on 11/05/2017.
 */
public class DBConnector {

    private Connection con;
    private DBConnector instance;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String username = "root";
    private String password = "admin";
    private String url = "jdbc:mysql://localhost:3306/weather?useSSL=false";

    private DBConnector() {
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {

            System.out.println("Error trying to open connection" + e.toString());

        }
    }



    public static DBConnector getInstance(){
        /*if(instance == null) {
            instance = new DBConnector();
        }
        return instance;*/
        return null;
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


    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
