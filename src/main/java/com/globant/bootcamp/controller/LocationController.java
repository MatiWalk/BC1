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
import java.util.List;

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
    public ResponseEntity<List<Location>> getLocations(){
        return new ResponseEntity<List<Location>>(locationClimateCRUD.selectAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST,  headers = {"content-type=application/json"})
    public int postLocation(@RequestBody Location location){
        return locationClimateCRUD.insert(location);
    }


    //get one, update one
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Location getLocation(@PathVariable("id") int id){
        return locationClimateCRUD.selectByID(id) ;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT,  headers = {"content-type=application/json"})
    public Location putLocation(@PathVariable("id") int id, @RequestBody Location location){
        locationClimateCRUD.update(location);
        return locationClimateCRUD.selectByID(id) ;
    }


}
