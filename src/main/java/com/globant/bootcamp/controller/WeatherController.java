package com.globant.bootcamp.controller;

import com.globant.bootcamp.builder.LocationBuilder;
import com.globant.bootcamp.client.YahooWeatherParser;
import com.globant.bootcamp.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mati on 25/05/2017.
 */

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private YahooWeatherParser adapterClientToParser;

    public WeatherController(YahooWeatherParser adapterClientToParser) {
        this.adapterClientToParser = adapterClientToParser;
    }

    @RequestMapping(value="/{country}/{zone}/{city}", method = RequestMethod.GET)
    public ResponseEntity<Location> getLocationWeather(@PathVariable("country") String country, @PathVariable("zone") String zone, @PathVariable("city") String city){

        Location location = adapterClientToParser.getData(LocationBuilder.builder().withCountry(country).withZone(zone).withCity(city).build());
        if (location == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(location);
    }

}
