package controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Mati on 11/05/2017.
 */
public class DBConnector {

    private Connection con;
    private static DBConnector instance;

    private DBConnector() {
        try {
            String username = "root";
            String password = "admin";
            String url = "jdbc:mysql://localhost:3306/weatherapi?useSSL=false";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {

            System.out.println("Error trying to open connection" + e.toString());

        }
    }

    public static DBConnector getInstance(){
        if(instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    public Connection getCon() {
        return con;
    }


}
