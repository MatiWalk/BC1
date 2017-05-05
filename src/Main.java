
import controller.DBController;
import model.*;
import model.unit.Temperature;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc;
    static DBController dbc;
    static ArrayList<WeatherCode> we;

    public static void main(String[] args) {

        sc = new Scanner(System.in);
        dbc = DBController.getInstance();
        we = DBController.getInstance().loadWeatherCodes();
        int o;
        boolean exit = true;

        while(exit) {
            System.out.println("Welcome to Java Bootcamp 2017 - Weather API - Cosnole ver. 0.8" +
                    "\n What would you like to do? " +
                    "\n 1- Load hardcoded data " +
                    "\n 2- Manually load data" +
                    "\n 3- Get stored result by ID " +
                    "\n Other- Exit");
            o = Integer.parseInt(sc.nextLine());

            switch (o) {
                case 1:
                    inputHardCode();
                    break;
                case 2:
                    inputManually();
                    break;
                case 3:
                    System.out.println("Write the ID to check (be sure it exists pls)");
                    int k = Integer.parseInt(sc.nextLine());
                    System.out.println("Stored result by id "+ k + ":");
                    System.out.println(dbc.loadResult(k).toString());
                    break;
                default: exit=!exit;
                    System.out.println("See you in hell");
                    break;
            }

        }



    }

    private static void inputHardCode(){
        System.out.println("Inserting predifined objects");
        Location l = new Location("Argentina", "Cordoba");
        Wind w = new Wind(65, 203, 7);
        Atmosphere at = new Atmosphere(66, 965.0f, barometricPressure.FALLING, 16.1f);
        Astronomy as = new Astronomy(LocalTime.of(7, 41), LocalTime.of(18, 45));
        Day d1 = new Day(we.get(30), LocalDate.of(2017, 04, 21), 64,
                64, 58);
        Day d2 = new Day(we.get(30), LocalDate.of(2017, 04, 22), -99,
                67, 53);
        ArrayList<Day> ad = new ArrayList<>();
        ad.add(d1);
        ad.add(d2);
        Units u = new Units(Temperature.F);
        Result r = new Result("Yahoo! Weather - Cordoba, CBA, AR", ad, l, w, at, as, LocalDateTime.now(), u);
        System.out.println("Hardcoded result is:");
        System.out.println(r.toString());
        dbc.insertResult(r);
        System.out.println("Last Insert query:");
        System.out.println(dbc.loadResult(dbc.getKey()).toString());
    }

    private static void inputManually(){
        int o;

        //objetos temporales

        Location t1 = null;
        Wind t2 = null;
        Atmosphere t3 = null;
        Astronomy t4 = null;
        ArrayList<Day> t5 = new ArrayList<>();
        Units t6 = null;
        Result t7;


        boolean exit = true;
        while (exit) {

            System.out.println("Options: \n" +
                    "1- Load Location\n" +
                    "2- Load Wind\n" +
                    "3- Load Atmosphere\n" +
                    "4- Load Astronomy\n" +
                    "5- Load Day\n" +
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
                    System.out.println("Day date:");
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

                    t5.add(new Day(we.get(wc), LocalDate.of(y, m, d), ct, ht, lt));

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
                        dbc.insertResult(t7);
                        System.out.println("Last Inserted:");
                        System.out.println(dbc.loadResult(dbc.getKey()).toString());
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
        }
    }
}
