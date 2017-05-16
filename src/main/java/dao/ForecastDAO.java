package dao;

import controller.DBConnector;
import model.Forecast;
import model.WeatherCode;

import java.sql.*;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class ForecastDAO implements ClimateDAO<Forecast> {

    List<Forecast> forecasts;
    Forecast forecast;
    Connection con = DBConnector.getInstance().getCon();

    @Override
    public int insert(Forecast forecast) {
        int key = -1;
        String insert = " insert into forecast (idforecastweather, date, hightemperature, lowtemperature) values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, forecast.getForecastWeather().getCode());
            ps.setDate(2, Date.valueOf(forecast.getDate()));
            ps.setInt(3, forecast.getHighTemperature());
            ps.setInt(4, forecast.getLowTemperature());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error inserting Forecast:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Forecast forecast, int id) {
        String update = " UPDATE forecast set idforecastweather = ?, date = ?, hightemperature = ?, " +
                "lowtemperature = ? where idforecast = ?";
        try {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setInt(1, forecast.getForecastWeather().getCode());
            ps.setDate(2, Date.valueOf(forecast.getDate()));
            ps.setInt(3, forecast.getHighTemperature());
            ps.setInt(4, forecast.getLowTemperature());
            ps.setInt(5, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error updating Forecast:");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete forecast where idforecast = ?";
        try {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error deleting Forecast:");
            ex.printStackTrace();
        }

    }

    @Override
    public Forecast selectByID(int id) {

        String select = "select * from forecast join weathercode on idforecast_weather where idforecast = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(select);
            while (rs.next()) {
                forecast = new Forecast.Builder<>()
                    .withForecastWeather(new WeatherCode.Builder().withCode());
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Forecast:");
            ex.printStackTrace();
        }
        return Forecast;
    }

    @Override
    public List<Forecast> selectAll() {
        return null;
    }
}
