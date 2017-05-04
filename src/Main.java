import controller.DBController;
import model.*;
import model.unit.Temperature;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Showing codes");
        ArrayList<WeatherCode> we = DBController.getInstance().loadWeatherCodes();
        System.out.println(we.toString());
        System.out.println("Inserting predifined objects");
        Location l = new Location("Argentina", "Cordoba");
        Wind w = new Wind(64, 203, 7);
        Atmosphere at = new Atmosphere(66, 965.0f, barometricPressure.FALLING, 16.1f);
        Astronomy as = new Astronomy(LocalTime.of(7, 41), LocalTime.of(18,45));
        Day d1 = new Day(we.get(30), LocalDate.of(2017, 04, 21), 64,
                58, 64 );
        Day d2 = new Day(we.get(30), LocalDate.of(2017, 04, 22), -99,
                53, 67 );
        ArrayList<Day> ad = new ArrayList<>();
        ad.add(d1);
        ad.add(d2);
        Units u = new Units(Temperature.F);
        Result r = new Result("Yahoo! Weather - Cordoba, CBA, AR", ad, l, w, at, as, LocalDateTime.now(), u);
        DBController.getInstance().insertResult(r);
        System.out.println("check database and pray to god");


    }
}
