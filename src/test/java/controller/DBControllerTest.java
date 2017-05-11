package controller;


import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.ConnectException;
import java.sql.*;

/**
 * Created by Mati on 11/05/2017.
 */
public class DBControllerTest {

    private static final String driver = "org.h2.Driver";
    private static final String url = "jdbc:h2:~/test";

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(url);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    @Before
    public void initialize(){
        try {
            RunScript.execute(getDBConnection(), new FileReader("src/test/resources/weatherDatabase.sql"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectTest(){
        String sql = "select * from weather_code where idweather_code = " + 1;
        try {
            Statement st = getDBConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error loading weather codes "+ ex);
        }
    }

}
