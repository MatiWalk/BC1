package com.globant.bootcamp.controller;

import com.globant.bootcamp.client.AdapterClientToParser;
import com.globant.bootcamp.client.YahooWeatherParser;
import com.globant.bootcamp.model.Location;
import org.easymock.EasyMock;
import org.easymock.internal.LastControl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 31/05/2017.
 */
public class WeatherControllerTest {

    private static String city = "test1";
    private static String zone = "test2";
    private static String country = "test3";
    //private YahooWeatherParser adapterClientToParser;




    @Test
    public void getLocationWeatherHappyTest(){
        Location locationExpected = new Location();
        locationExpected.setCity(city);
        locationExpected.setZone(zone);
        locationExpected.setCountry(country);

        YahooWeatherParser adapterClientToParser = EasyMock.createMock(AdapterClientToParser.class);
        EasyMock.expect(adapterClientToParser.getData(EasyMock.anyObject())).andReturn(locationExpected);
        EasyMock.replay(adapterClientToParser);

        WeatherController weatherController = new WeatherController(adapterClientToParser);
        Location locationResult = (Location) weatherController.getLocationWeather(country, zone, city).getBody();

        assertEquals(city, locationResult.getCity());
        assertEquals(zone, locationResult.getZone());
        assertEquals(country, locationResult.getCountry());
        EasyMock.verify(adapterClientToParser);
    }


    @Test
    public void getLocationWeatherSadTest(){
        YahooWeatherParser adapterClientToParser = EasyMock.createMock(AdapterClientToParser.class);

        EasyMock.expect(adapterClientToParser.getData(EasyMock.anyObject())).andReturn(null);
        EasyMock.replay(adapterClientToParser);

        WeatherController weatherController = new WeatherController(adapterClientToParser);
        ResponseEntity responseEntity = weatherController.getLocationWeather(country, zone, city);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No data found for that location", responseEntity.getBody());
        EasyMock.verify(adapterClientToParser);

    }
}
