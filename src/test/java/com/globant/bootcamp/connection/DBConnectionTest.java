package com.globant.bootcamp.connection;

import org.h2.tools.RunScript;
import org.junit.BeforeClass;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Mati on 28/05/2017.
 */
public class DBConnectionTest {

    private static final String driver = "org.h2.Driver";
    private static final String username = "root";
    private static final String password = "admin";
    private static final String url = "jdbc:h2:mem:test_mem";


    public static Connection initializeH2(){

        DBConnector dbc= new DBConnector(driver, username, password, url);

        try {
            RunScript.execute(dbc.getCon(), new FileReader("src/test/resources/TestDatabase.sql"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dbc.getCon();
    }
}
