package com.globant.bootcamp.persistence;

import com.globant.bootcamp.builder.ResultBuilder;
import com.globant.bootcamp.builder.UnitsBuilder;
import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.Forecast;
import com.globant.bootcamp.model.Location;
import com.globant.bootcamp.model.Result;
import com.globant.bootcamp.model.Today;
import com.globant.bootcamp.model.unit.Temperature;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
@Component
public class ResultCRUD implements ClimateCRUD<Result> {

    private Connection con;
    private ClimateCRUD<Location> locationClimateCRUD /*= new LocationCRUD()*/;
    private ClimateCRUD<Today> todayClimateCRUD /*= new TodayCRUD()*/;
    private ClimateCRUD<Forecast> forecastClimateCRUD /*= new ForecastCRUD()*/;
    private Result result;
    private List<Result> results;

    public ResultCRUD(Connection con, ClimateCRUD<Location> locationClimateCRUD, ClimateCRUD<Today> todayClimateCRUD, ClimateCRUD<Forecast> forecastClimateCRUD) {
        this.con = con;
        this.locationClimateCRUD = locationClimateCRUD;
        this.todayClimateCRUD = todayClimateCRUD;
        this.forecastClimateCRUD = forecastClimateCRUD;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    public int insert(Result result) {
        int key = -1;
        String insert = " insert into result (title, idlocation, idtoday, pubdate, units) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, result.getTitle());
            ps.setInt(2, locationClimateCRUD.insert(result.getLocation()));
            ps.setInt(3, todayClimateCRUD.insert(result.getToday()));
            ps.setTimestamp(4, Timestamp.valueOf(result.getPuDate()));
            ps.setByte(5, (byte) result.getUnits().getTemperatureUnit().ordinal());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }
            for (Forecast f:result.getForecasts()) {
                forecastClimateCRUD.insert(f);
            }

        } catch (SQLException ex) {
            System.out.println("Error inserting result:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Result result, int id) {
        String update = " UPDATE result set title = ?, idlocation = ?, idtoday = ?, pubdate = ?, units = ?  where idresult = ?";
        try {
            PreparedStatement ps = con.prepareStatement(update, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, result.getTitle());
            ps.setInt(2, locationClimateCRUD.insert(result.getLocation()));
            ps.setInt(3, todayClimateCRUD.insert(result.getToday()));
            for (Forecast f:result.getForecasts()) {
                ;
            }
            ps.setTimestamp(4, Timestamp.valueOf(result.getPuDate()));
            ps.setByte(5, (byte) result.getUnits().getTemperatureUnit().ordinal());
            ps.setInt(6, id);
            ps.executeUpdate();

            int[] fk = childkeys(id);
            for (int i = 0; i<fk.length; i++){
                forecastClimateCRUD.update(result.getForecasts().get(i), fk[i]);
            }

        } catch (SQLException ex) {
            System.out.println("Error updating result:");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete result where idresult = ?";
        try {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error deleting result:");
            ex.printStackTrace();
        }
    }


    @Override
    public Result selectByID(int id) {
        String select = "select * from result where idresult = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<Forecast> forecasts = new LinkedList<>();
                int[] fk = childkeys(id);
                for (int i = 0; i<fk.length; i++){
                    Forecast f = forecastClimateCRUD.selectByID(fk[i]);
                    forecasts.add(f);
                }
                result = ResultBuilder.builder()
                        .withTitle(rs.getString(2))
                        .withLocation(locationClimateCRUD.selectByID(rs.getInt(3)))
                        .withToday(todayClimateCRUD.selectByID(rs.getInt(4)))
                        .withForecasts(forecasts)
                        .withPubDate(rs.getTimestamp(5).toLocalDateTime())
                        .withUnits(UnitsBuilder.builder()
                                .withTemperatureUnit(Temperature.values()[rs.getByte(6)])
                                .build())
                        .build();

            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving result:");
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Result> selectAll() {
        results = new LinkedList<>();
        String select = "select * from forecast";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<Forecast> forecasts = new LinkedList<>();
                int[] fk = childkeys(rs.getInt(1));
                for (int i = 0; i<fk.length; i++){
                    Forecast f = forecastClimateCRUD.selectByID(fk[i]);
                    forecasts.add(f);
                }

                result = ResultBuilder.builder()
                        .withTitle(rs.getString(2))
                        .withLocation(locationClimateCRUD.selectByID(rs.getInt(3)))
                        .withToday(todayClimateCRUD.selectByID(rs.getInt(4)))
                        .withForecasts(forecasts)
                        .withPubDate(rs.getTimestamp(5).toLocalDateTime())
                        .withUnits(UnitsBuilder.builder()
                                .withTemperatureUnit(Temperature.values()[rs.getByte(5)])
                                .build())
                        .build();
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving results:");
            ex.printStackTrace();
        }
        return results;
    }

    private int[] childkeys(int id){

        int amount = 0;
        int[] fk = null;
        String count = "SELECT count(*) FROM forecast where idresult = ? ";
        String select = "select idforecast from forecast f join result r on r.idresult = f.idresult where idtoday = ?";
        try {
            PreparedStatement ps = con.prepareStatement(count);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                amount = rs.getInt(1);
            }
            fk = new int[amount];


            ps = con.prepareStatement(select);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                fk[i] = rs.getInt(1);
                i++;
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error getting foreign keys");
            ex.printStackTrace();
        }
    return fk;
    }



}
