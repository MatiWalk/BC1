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
    private static DBConnector instance;
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "admin";
    private static String url = "jdbc:mysql://localhost:3306/weatherapi?useSSL=false";

    private DBConnector() {
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {

            System.out.println("Error trying to open connection" + e.toString());

        }
    }

    //used for testing
    public static void setTestStrings(String dr, String un, String pw, String u){
        driver = dr;
        username = un;
        password = pw;
        url = u;
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

/*
    public DBManager(){
        con = DBConnector.getInstance().getCon();
        loadWeatherCodes();
    }

    public DBManager(Connection con){
        this.con = con;
        loadWeatherCodes();
    }

    public int getKey() {
        return key;
    }

    public void insertResult (Result result) {

        String query = " insert into Result (title, country, city, astronomy_sunrise, astronomy_sunset," +
                "wind_chill, wind_direction, wind_speed, atmosphere_humidity, atmosphere_pressure," +
                "atmosphere_barometricpressure, atmosphere_visibility, pubDate, unit)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {

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

    }

    private void insertDays(LinkedList<Today> days, int key){
        String query = " insert into day ( day , idweather_code, current_temperature, high_temperature," +
                "low_temperature, idresult)"
                + " values (?, ?, ?, ?, ?, ?)";
        try{
            for (Today d: days) {
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

    }

    public LinkedList<WeatherCode> loadWeatherCodes () {
        String sql = "select * from weather_code";
        we = new LinkedList<>();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                WeatherCode wc = new WeatherCode();
                wc.setCode(rs.getInt(1));
                wc.setWeather(rs.getString(2));
                we.add(wc);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error loading weather codes "+ ex);
        }

        return we;
    }


    //No usage RN
    public WeatherCode loadWeatherCode (int code) {
        String sql = "select * from weather_code where idweather_code = " + code;
        WeatherCode weatherCode = new WeatherCode();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                weatherCode.setCode(rs.getInt(1));
                weatherCode.setWeather(rs.getString(2));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error loading weather codes "+ ex);
        }

        return weatherCode;
    }

    public Result loadResult(int k) {

        //Segun el ID del ultimo insert
        //busca los dias
        String sql = "SELECT * FROM day where idresult = "+ k;
        LinkedList<Today> days = new LinkedList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Today t = new Today();
                t.setDate(rs.getDate(2).toLocalDate());
                t.setWeatherCode(we.get(rs.getInt(3)));
                t.setCurrentTemperature(rs.getInt(4));
                t.setHighTemperature(rs.getInt(5));
                t.setLowTemperature(rs.getInt(6));

                days.add(t);

            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving days "+ ex);
        }

        //busca el resultado
        sql = "SELECT * FROM result  where idresult = "+ k;
        Result r = new Result();
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

        return r;
    }*/


}
