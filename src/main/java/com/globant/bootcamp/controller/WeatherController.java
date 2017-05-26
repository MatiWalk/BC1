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
    public ResponseEntity<Location> postLocation(@PathVariable("country") String country, @PathVariable("zone") String zone, @PathVariable("city") String city){

        Location l = clientHandler.getData(country, zone, city);
        if (l == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

}
