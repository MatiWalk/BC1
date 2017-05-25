package com.globant.bootcamp.controller;

import com.globant.bootcamp.builder.ForecastBuilder;
import com.globant.bootcamp.builder.LocationBuilder;
import com.globant.bootcamp.builder.TodayBuilder;
import com.globant.bootcamp.client.ClientHandler;
import com.globant.bootcamp.model.Forecast;
import com.globant.bootcamp.model.Location;
import com.globant.bootcamp.model.Today;
import com.globant.bootcamp.persistence.ClimateCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mati on 25/05/2017.
 */

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    @Qualifier("locationCRUD")
    private ClimateCRUD<Location> locationClimateCRUD;

    @Autowired
    @Qualifier("todayCRUD")
    private ClimateCRUD<Today> todayClimateCRUD;

    @Autowired
    @Qualifier("forecastCRUD")
    private ClimateCRUD<Forecast> forecastClimateCRUD;

    @Autowired
    private ClientHandler clientHandler;

    @RequestMapping(value="/{country}/{zone}/{city}", method = RequestMethod.GET)
    public ResponseEntity<Location> postLocation(@PathVariable("country") String country, @PathVariable("zone") String zone, @PathVariable("city") String city, @RequestParam("forecast") int amountForecast){
        System.out.println(clientHandler.getData().toString());
        Location l = locationClimateCRUD.selectByObject(LocationBuilder.builder().withCountry(country).withZone(zone).withCity(city).build());
        l.setToday(todayClimateCRUD.selectByObject(TodayBuilder.builder().withWOEID(l.getWoeid()).withDate(LocalDate.now()).build()));
        List<Forecast> forecasts = new LinkedList<>();
        for (int i = 0; i < amountForecast; i++ ){
            forecasts.add(forecastClimateCRUD.selectByObject(ForecastBuilder.builder().withWOEID(l.getWoeid()).withDate(LocalDate.now().plusDays(i)).build()));
        }
        l.setForecasts(forecasts);
        return new ResponseEntity<Location>(l, HttpStatus.OK);
    }

}
