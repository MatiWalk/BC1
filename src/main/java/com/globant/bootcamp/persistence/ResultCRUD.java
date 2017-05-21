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
public class ResultCRUD extends QueryExecuter implements ClimateCRUD<Result> {

    private ClimateCRUD<Location> locationClimateCRUD /*= new LocationCRUD()*/;
    private ClimateCRUD<Today> todayClimateCRUD /*= new TodayCRUD()*/;
    private ClimateCRUD<Forecast> forecastClimateCRUD /*= new ForecastCRUD()*/;
    private Result result;
    private List<Result> results;

    public ResultCRUD(Connection con, ClimateCRUD<Location> locationClimateCRUD, ClimateCRUD<Today> todayClimateCRUD, ClimateCRUD<Forecast> forecastClimateCRUD) {
        super(con);
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
            key=executeResult(insert, result.getTitle(), locationClimateCRUD.insert(result.getLocation()),
                    todayClimateCRUD.insert(result.getToday()), result.getPuDate(), (byte) result.getUnits().getTemperatureUnit().ordinal());
            for (Forecast f:result.getForecasts()) {
                forecastClimateCRUD.insert(f);
            }
        } catch (SQLException ex) {
            System.out.println("Error inserting Result:");
            ex.printStackTrace();
        }

        return key;
    }

    @Override
    public void update(Result result, int id) {
        String update = " UPDATE result set title = ?, pubdate = ?, units = ?  where idresult = ?";
        int[] fkeys = getForeignKeys(id);
        todayClimateCRUD.update(result.getToday(), fkeys[0]);
        locationClimateCRUD.update(result.getLocation(), fkeys[1]);

        try{
            executeUpdate(update, result.getTitle(), result.getPuDate(),
                    (byte)result.getUnits().getTemperatureUnit().ordinal(), id);
            int[] fk = childkeys(id);
            for (int i = 0; i<fk.length; i++){
                forecastClimateCRUD.update(result.getForecasts().get(i), fk[i]);
            }
        } catch (SQLException ex) {
            System.out.println("Error updating Result:");
            ex.printStackTrace();
        }

    }

    @Override
    public void deleteByID(int id) {
        String delete = " delete result where idresult = ?";
        try {
            executeDelete(delete, id);
        } catch (SQLException e) {
            System.out.println("Error deleting Result:");
            e.printStackTrace();
        }
    }


    @Override
    public Result selectByID(int id) {
        String select = "select * from result where idresult = ?";

        try {
            ResultSet rs = executeSelectByID(select, id);
            while (rs.next()) {
                int[] fk = childkeys(id);
                List<Forecast> forecasts = new LinkedList<>();
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
        } catch (SQLException e) {
            System.out.println("Error selecting one result:");
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public List<Result> selectAll() {
        results = new LinkedList<>();
        String select = "select * from forecast";

        try {
            ResultSet rs = executeSelectAll(select);
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
        } catch (SQLException e) {
            System.out.println("Error selecting all Result:");
            e.printStackTrace();
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

    private int[] getForeignKeys(int id){
        int[] fk = new int[2];
        String select = "select idtoday, location from result where idresult = ?";
        try {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fk[0] = rs.getInt(1);
                fk[1] = rs.getInt(2);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error getting foreign keys for result");
            ex.printStackTrace();
        }
        return fk;
    }



}
