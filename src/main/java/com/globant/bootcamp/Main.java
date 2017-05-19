package com.globant.bootcamp;

import com.globant.bootcamp.builder.*;
import com.globant.bootcamp.model.WeatherCode;
import com.globant.bootcamp.model.*;
import com.globant.bootcamp.model.unit.Temperature;
import com.globant.bootcamp.persistence.ClimateCRUD;
import com.globant.bootcamp.persistence.ResultCRUD;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static Scanner sc;

    static ClimateCRUD<Result> resultClimateCRUD = new ResultCRUD();



    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        sc = new Scanner(System.in);
        int o;
        boolean exit = true;

        while(exit) {
            System.out.println("Welcome to Java Bootcamp 2017 - Weather API - Cosnole ver. 0.8" +
                    "\n What would you like to do? " +
                    "\n 1- Load hardcoded data " +
                    "\n Disabled- Manually load data" +
                    "\n 3- Get stored result by ID " +
                    "\n Other- Exit");
            o = Integer.parseInt(sc.nextLine());

            switch (o) {
                case 1:
                    inputHardCode();
                    break;
                case 2:
                    //inputManually();
                    break;
                case 3:
                    System.out.println("Write the ID to check (be sure it exists pls)");
                    int k = Integer.parseInt(sc.nextLine());
                    System.out.println("Stored result by id "+ k + ":");
                    System.out.println(resultClimateCRUD.selectByID(k));
                    break;
                default: exit=!exit;
                    System.out.println("See you in hell");
                    break;
            }

        }



    }

    private static void inputHardCode(){
        System.out.println("Inserting predifined objects");

        LinkedList<Forecast> fc = new LinkedList<>();
        Today td1 = TodayBuilder.builder()
                .withDate(LocalDateTime.of(2017, 04, 21, 23, 23))
                .withCurrentWeather(WeatherCodeBuilder.builder()
                                .withCode(30)
                                .withWeather("Sunny")
                                .build())
                .withCurrentTemperature(28)
                .withAstronomy(AstronomyBuilder.builder()
                            .withSunrise(LocalTime.of(10, 10))
                            .withSunset(LocalTime.of(20, 20))
                            .build())
                .withAtmosphere(AtmosphereBuilder.builder()
                                .withHumidity(65)
                                .withPressure(965.0f)
                                .withRising(barometricPressure.RISING)
                                .withVisibility(0.9f)
                                .build())
                .withWind(WindBuilder.builder()
                        .withChill(65)
                        .withDirection(203)
                        .withSpeed(7)
                        .build())
                .build();
        Forecast f1 = ForecastBuilder.builder()
                    .withDate(LocalDate.of(2017, 04, 22))
                    .withForecastWeather(WeatherCodeBuilder.builder()
                            .withCode(30)
                            .withWeather("Sunny")
                            .build())
                    .withHighTemperature(21)
                    .withLowTemperature(12)
                    .build();
        Forecast f2 = ForecastBuilder.builder()
                .withDate(LocalDate.of(2017, 04, 23))
                .withForecastWeather(WeatherCodeBuilder.builder()
                        .withCode(22)
                        .withWeather("cloudy")
                        .build())
                .withHighTemperature(32)
                .withLowTemperature(23)
                .build();
        fc.add(f1);
        fc.add(f2);

        Result result= ResultBuilder.builder()
                .withTitle("Yahoo! Weather - Cordoba, CBA, AR")
                .withLocation(LocationBuilder.builder()
                                .withCountry("Argentina")
                                .withZone("CBA")
                                .withCity("Cordoba")
                                .build())
                .withToday(td1)
                .withForecasts(fc)
                .withPubDate(LocalDateTime.now())
                .withUnits(UnitsBuilder.builder()
                        .withTemperatureUnit(Temperature.C)
                        .build())
                .build();

        System.out.println("Hardcoded result is:");
        System.out.println(result.toString());
        int i = resultClimateCRUD.insert(result);
        System.out.println(i);
        System.out.println("Last Insert query:");
        System.out.println(resultClimateCRUD.selectByID(i));
    }

    private static void inputManually(){
        int o;


/*

        boolean exit = true;
        while (exit) {

            System.out.println("Options: \n" +
                    "1- Load Location\n" +
                    "2- Load Wind\n" +
                    "3- Load Atmosphere\n" +
                    "4- Load Astronomy\n" +
                    "5- Load Today\n" +
                    "6- Load Unit standard\n" +
                    "7- Insert Result (if everything else is loaded)\n" +
                    "Other- Exit \n" +
                    "No validations, Don't break my program please :^)");
            o = Integer.parseInt(sc.nextLine());

            switch (o) {
                case 1:
                    //Location
                    if (t1 == null){
                        t1 = new Location();
                        System.out.println("Country?");
                        String country = sc.nextLine();
                        System.out.println("City?");
                        String city = sc.nextLine();
                        System.out.println(country + city);
                        t1 = new Location(country, city);
                    }else {
                        System.out.println("Already Loaded. Try Another Option1");
                    }
                    break;
                case 2:
                    //Wind
                    if (t2 == null){
                        System.out.println("Input Integers please");
                        System.out.println("Chill?");
                        int c = Integer.parseInt(sc.nextLine());
                        System.out.println("Direction?");
                        int d = Integer.parseInt(sc.nextLine());
                        System.out.println("Speed?");
                        int s = Integer.parseInt(sc.nextLine());
                        t2 = new Wind(c, d, s);
                    }else {
                        System.out.println("Already Loaded. Try Another Option");
                    }
                    break;
                case 3:
                    //Atmosphere
                    if (t3 == null){
                        System.out.println("Humidity? (Integer please)");
                        int h = Integer.parseInt(sc.nextLine());
                        System.out.println("Pressure? (Float please)");
                        float p = Float.parseFloat(sc.nextLine());
                        System.out.println("Rising? 0: Steady 1: Rising 2: Falling");
                        int bp = Integer.parseInt(sc.nextLine());
                        System.out.println("Visibility? (Float please)");
                        float v = Float.parseFloat(sc.nextLine());
                        t3 = new Atmosphere(h, p, barometricPressure.values()[bp], v);
                    }else {
                        System.out.println("Already Loaded. Try Another Option");
                    }
                    break;
                case 4:
                    //Astronomy
                    if (t4 == null){
                        System.out.println("SunRise time.");
                        System.out.println("Insert Hour (24hs Format)");
                        int hr = Integer.parseInt(sc.nextLine());
                        System.out.println("Insert Minutes");
                        int mr = Integer.parseInt(sc.nextLine());
                        System.out.println("SunSet time.");
                        System.out.println("Insert Hour (24hs Format)");
                        int hs = Integer.parseInt(sc.nextLine());
                        System.out.println("Insert Minutes");
                        int ms = Integer.parseInt(sc.nextLine());
                        t4 = new Astronomy(LocalTime.of(hr, mr), LocalTime.of(hs, ms));
                    }else {
                        System.out.println("Already Loaded. Try Another Option");
                    }
                    break;
                case 5:
                    //Days
                    System.out.println("Insert weather code (Integer Please, according to documentation)");
                    int wc = Integer.parseInt(sc.nextLine());
                    System.out.println("Today date:");
                    System.out.println("Insert year");
                    int y = Integer.parseInt(sc.nextLine());
                    System.out.println("Insert month");
                    int m = Integer.parseInt(sc.nextLine());
                    System.out.println("Insert day");
                    int d = Integer.parseInt(sc.nextLine());
                    int ct = -1000;
                    if (t5.isEmpty()){
                        System.out.println("Current temperature? (Integer please)");
                        ct = Integer.parseInt(sc.nextLine());
                    }
                    System.out.println("High temperature? (Integer please)");
                    int ht = Integer.parseInt(sc.nextLine());
                    System.out.println("Low temperature? (Integer please)");
                    int lt = Integer.parseInt(sc.nextLine());

                    t5.add(new Today(we.get(wc), LocalDate.of(y, m, d), ct, ht, lt));

                    break;
                case 6:
                    //Units
                    if (t6 == null){
                        System.out.println("0: English Units   1: Metric Units");
                        int un = Integer.parseInt(sc.nextLine());
                        t6 = new Units(Temperature.values()[un]);
                    }else {
                        System.out.println("Already Loaded. Try Another Option");
                    }
                    break;
                case 7:
                    //Result insert
                    if(t1 != null && t2 != null && t3 != null && t4 != null && !t5.isEmpty() && t6 != null ){
                        System.out.println("Insert Title for these results");
                        String t = sc.nextLine();
                        t7 = new Result(t, t5, t1, t2, t3, t4, LocalDateTime.now(), t6);
                        Main.dbm.insertResult(t7);
                        System.out.println("Last Inserted:");
                        System.out.println(Main.dbm.loadResult(Main.dbm.getKey()).toString());
                        exit = !exit;
                    }
                    else{
                        System.out.println("Load all the other options please");
                    }
                    break;
                default:
                    exit = !exit;
                    break;
            }
        }*/
    }
}
