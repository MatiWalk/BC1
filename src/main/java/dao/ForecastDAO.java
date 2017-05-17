package dao;

import builder.ForecastBuilder;
import builder.WeatherCodeBuilder;
import controller.DBConnector;
import model.Forecast;
import model.WeatherCode;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class ForecastDAO implements ClimateDAO<Forecast> {

    private List<Forecast> forecasts;
    private Forecast forecast;
    private Connection con = DBConnector.getInstance().getCon();

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

        String select = "select * from forecast join weather_code on idforecastweather where idforecast = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(select);
            while (rs.next()) {
                forecast = ForecastBuilder.builder()
                    .withForecastWeather(
                            WeatherCodeBuilder.builder()
                            .withCode(rs.getInt(2))
                            .withWeather(rs.getString(3))
                            .build())
                    .withDate(rs.getDate(5).toLocalDate())
                    .withHighTemperature(rs.getInt(3))
                    .withLowTemperature(rs.getInt(4))
                    .build();
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Forecast:");
            ex.printStackTrace();
        }
        return forecast;
    }

    @Override
    public List<Forecast> selectAll() {
        forecasts = new LinkedList<>();
        String select = "select * from forecast";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery(select);
            while (rs.next()) {
                forecast = ForecastBuilder.builder()
                        .withForecastWeather(WeatherCode.weatherCodes.get(rs.getInt(2)))
                        .withDate(rs.getDate(5).toLocalDate())
                        .withHighTemperature(rs.getInt(3))
                        .withLowTemperature(rs.getInt(4))
                        .build();
                forecasts.add(forecast);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving Forecasts:");
            ex.printStackTrace();
        }
        return forecasts;
    }
}
