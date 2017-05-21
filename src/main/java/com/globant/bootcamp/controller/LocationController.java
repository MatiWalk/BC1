package com.globant.bootcamp.controller;

import com.globant.bootcamp.builder.LocationBuilder;
import com.globant.bootcamp.model.Location;
import com.globant.bootcamp.persistence.ClimateCRUD;
import com.globant.bootcamp.persistence.LocationCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;

/**
 * Created by Mati on 21/05/2017.
 */
@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    @Qualifier("locationCRUD")
    private ClimateCRUD<Location> locationClimateCRUD;

    @RequestMapping
    public ResponseEntity<Location> getLocationTest(@RequestParam int id){
        return new ResponseEntity<Location>(locationClimateCRUD.selectByID(id), HttpStatus.OK);
    }

    //deberia haber un gettodos y get 1 solo por nombre?"

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public int postLocationTest(@RequestBody Location location){
        return locationClimateCRUD.insert(location);
    }
}
