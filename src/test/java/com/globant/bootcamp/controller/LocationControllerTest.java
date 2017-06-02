package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.Location;
import com.globant.bootcamp.persistence.ClimateCRUD;
import com.globant.bootcamp.persistence.LocationCRUD;
import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;
import org.easymock.internal.LastControl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 31/05/2017.
 */
public class LocationControllerTest {

    private int woeid = 10;
    //private ClimateCRUD<Location> locationClimateCRUDMock;





    @Test
    public void postLocationHappyTest(){
        ClimateCRUD<Location> locationClimateCRUDMock = EasyMock.createMock(LocationCRUD.class);
        EasyMock.expect(locationClimateCRUDMock.insert(EasyMock.anyObject())).andReturn(woeid);
        EasyMock.replay(locationClimateCRUDMock);
        Location location = new Location();
        location.setWoeid(woeid);
        LocationController locationController = new LocationController(locationClimateCRUDMock);
        HttpStatus expected = locationController.postLocation(location);
        assertEquals(HttpStatus.OK, expected);
        EasyMock.verify(locationClimateCRUDMock);
    }




    @Test
    public void postLocationSadTest(){
        ClimateCRUD<Location> locationClimateCRUDMock = EasyMock.createMock(LocationCRUD.class);
        EasyMock.expect(locationClimateCRUDMock.insert(EasyMock.anyObject())).andReturn(-1);
        EasyMock.replay(locationClimateCRUDMock);
        Location location = new Location();
        location.setWoeid(woeid);
        LocationController locationController = new LocationController(locationClimateCRUDMock);
        HttpStatus expected = locationController.postLocation(location);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, expected);
        EasyMock.verify(locationClimateCRUDMock);
    }


    @Before
    public void beforePut(){
        LastControl.pullMatchers();
    }

    @Test
    public void putLocationTest(){
        Location locationExpected = new Location();
        locationExpected.setWoeid(woeid);
        locationExpected.setCountry("asd");
        locationExpected.setZone("das");
        locationExpected.setCity("sad");
        ClimateCRUD<Location> locationClimateCRUDMock = EasyMock.createMock(LocationCRUD.class);
        EasyMock.expect(locationClimateCRUDMock.update(locationExpected)).andReturn(true);
        EasyMock.expect(locationClimateCRUDMock.selectByID(locationExpected.getWoeid())).andReturn(locationExpected);
        EasyMock.replay(locationClimateCRUDMock);
        LocationController locationController = new LocationController(locationClimateCRUDMock);
        Location locationResult = locationController.putLocation(locationExpected).getBody();
        assertEquals(locationExpected, locationResult);
        EasyMock.verify(locationClimateCRUDMock);
    }

}
