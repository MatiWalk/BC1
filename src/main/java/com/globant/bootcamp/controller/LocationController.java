package com.globant.bootcamp.controller;

import com.globant.bootcamp.builder.LocationBuilder;
import com.globant.bootcamp.client.FormatHelper;
import com.globant.bootcamp.model.Location;
import com.globant.bootcamp.persistence.ClimateCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
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

    public LocationController(ClimateCRUD<Location> locationClimateCRUD) {
        this.locationClimateCRUD = locationClimateCRUD;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getLocations(){
        List<Location> result = locationClimateCRUD.selectAll();
        if (result.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No location data");
        else return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @RequestMapping(value="/add", method = RequestMethod.POST,  headers = {"content-type=application/json"})
    public ResponseEntity postLocation(@RequestBody Location location){
        int woeid = locationClimateCRUD.insert(location);
        if (woeid < 0 ) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error inserting Location");
        else return ResponseEntity.status(HttpStatus.OK).body("Insert successful");
    }

    @RequestMapping(value="/{country}/{zone}/{city}", method = RequestMethod.GET)
    public ResponseEntity getLocation(@PathVariable("country") String country,@PathVariable("zone") String zone,@PathVariable("city") String city){
        Location locationInput = LocationBuilder.builder().withCountry(country).withZone(zone).withCity(city).build();
        FormatHelper.standarizeLocationStrings(locationInput);
        Location location = locationClimateCRUD.selectByObject(locationInput);
        if (location == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("That location wasn't found");
        return ResponseEntity.status(HttpStatus.OK).body(location);
    }

    @RequestMapping(value="/update", method = RequestMethod.PUT,  headers = {"content-type=application/json"})
    public ResponseEntity putLocation(@RequestBody Location location){
        boolean isUpdated = locationClimateCRUD.update(location);
        if (isUpdated) return ResponseEntity.ok().body(locationClimateCRUD.selectByID(location.getWoeid()));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error Updating location");
    }


}
