package controller;
import model.*;
import model.unit.Temperature;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Mati on 02/05/2017.
 */


public class DBController {

    private Connection con;
    private int key;
    private static DBController instance;

    private DBController() {
    }

    public static DBController getInstance(){
        if(instance == null) {
            instance = new DBController();
        }
        return instance;
    }

    private void openConnection() {
        try {
            String username = "root";
            String password = "admin";
            String url = "jdbc:mysql://localhost:3306/weatherapi";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Successful connection");
        } catch (Exception e) {

            System.out.println(e.toString());

        }
    }

    private void closeConnection() {
        try {
            con.close();
            System.out.println("Connection closed");
        } catch (Exception e) {
            System.out.println("Error trying to close connection");
        }

    }

    public void insertResult (Result result) {

        String query = " insert into Result (title, country, city, astronomy_sunrise, astronomy_sunset," +
                "wind_chill, wind_direction, wind_speed, atmosphere_humidity, atmosphere_pressure," +
                "atmosphere_barometricpressure, atmosphere_visibility, pubDate, unit)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            openConnection();
            PreparedStatement preparedStmt  = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, result.getTitle());
            //location
            preparedStmt.setString(2, result.getLocation().getCountry());
            preparedStmt.setString(3, result.getLocation().getCity());
            //astronomy
            preparedStmt.setTime (4, Time.valueOf(result.getAstronomy().getSunRise()));
            preparedStmt.setTime (5, Time.valueOf(result.getAstronomy().getSunSet()) );
            //wind
            preparedStmt.setInt(6, result.getWind().getChill());
            preparedStmt.setInt(7, result.getWind().getDirection());
            preparedStmt.setInt(8, result.getWind().getSpeed());
            //atmosphere
            preparedStmt.setInt(9, result.getAtmosphere().getHumidity());
            preparedStmt.setFloat(10, result.getAtmosphere().getPressure());
            preparedStmt.setInt(11, result.getAtmosphere().getRising().ordinal());
            preparedStmt.setFloat(12, result.getAtmosphere().getVisibility());
            //publish date
            preparedStmt.setTimestamp(13, Timestamp.valueOf(result.getPuDate()));
            //unit
            preparedStmt.setByte(14, (byte)result.getUnits().getTemperatureUnit().ordinal());

            preparedStmt.execute();

            ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }
            insertDays(result.getDays(), key);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    private void insertDays(ArrayList<Day> days, int key){
        String query = " insert into day ( day , idweather_code, current_temperature, high_temperature," +
                "low_temperature, idresult)"
                + " values (?, ?, ?, ?, ?, ?)";

        openConnection();
        try{
            for (Day d: days) {
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setDate(1, Date.valueOf(d.getDate()));
                preparedStmt.setInt(2, d.getWeatherCode().getCode());
                preparedStmt.setInt(3, d.getCurrentTemperature());
                preparedStmt.setInt(4, d.getHighTemperature());
                preparedStmt.setInt(5, d.getLowTemperature());
                preparedStmt.setInt(6, key);
                preparedStmt.execute();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    public ArrayList<WeatherCode> loadWeatherCodes () {
        String sql = "select * from weather_code";
        ArrayList<WeatherCode> weatherCodes = new ArrayList<>();
        openConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                WeatherCode wc = new WeatherCode();
                wc.setCode(rs.getInt(1));
                wc.setText(rs.getString(2));
                weatherCodes.add(wc);

            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error loading weather codes "+ ex);
        }

        closeConnection();
        return weatherCodes;
    }

    public WeatherCode loadWeatherCode (int code) {
        String sql = "select * from weather_code where idweather_code = " + code;
        WeatherCode weatherCode = new WeatherCode();
        openConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                weatherCode.setCode(rs.getInt(1));
                weatherCode.setText(rs.getString(2));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error loading weather codes "+ ex);
        }

        closeConnection();
        return weatherCode;
    }

    public Result loadLastInsert () {

        //busca los dias
        String sql = "SELECT * FROM day where idresult = "+ key;
        ArrayList<Day> days = new ArrayList<>();
        openConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Day t = new Day();

                t.setDate(rs.getDate(2).toLocalDate());
                //busca el weathercode especifico de este dia
                WeatherCode w = loadWeatherCode(rs.getInt(3));
                t.setWeatherCode(w);
                t.setCurrentTemperature(rs.getInt(4));
                t.setLowTemperature(rs.getInt(5));
                t.setHighTemperature(rs.getInt(6));

                days.add(t);

            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving days "+ ex);
        }
        closeConnection();

        //busca el resultado
        sql = "SELECT * FROM result  where idresult = "+ key;
        Result r = new Result();
        openConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                r.setTitle(rs.getString(2));

                Location l = new Location(rs.getString(3), rs.getString(4));
                Astronomy as = new Astronomy(rs.getTime(5).toLocalTime(), rs.getTime(6).toLocalTime());
                Wind w = new Wind(rs.getInt(7), rs.getInt(8), rs.getInt(9));
                Atmosphere at = new Atmosphere(rs.getInt(10), rs.getFloat(11),
                        barometricPressure.values()[rs.getInt(12)], rs.getFloat(13));
                r.setPuDate(rs.getTimestamp(14).toLocalDateTime());
                Units u = new Units(Temperature.values()[rs.getByte(15)]);
                r.setLocation(l);
                r.setAstronomy(as);
                r.setWind(w);
                r.setAtmosphere(at);
                r.setUnits(u);
                r.setDays(days);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving result "+ ex);
        }
        closeConnection();

        return r;
    }


}