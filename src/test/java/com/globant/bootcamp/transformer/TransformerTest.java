package com.globant.bootcamp.transformer;

import com.globant.bootcamp.jsonDTO.*;
import com.globant.bootcamp.model.Location;
import com.globant.bootcamp.transformers.Transformer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 29/05/2017.
 */
public class TransformerTest {
    JsonResponse jsonResponse;
    JsonWind jsonWind;
    JsonAtmosphere jsonAtmosphere;
    JsonAstronomy jsonAstronomy;
    JsonLocation jsonLocation;
    JsonItem jsonItem;
    JsonCondition jsonCondition;
    JsonForecast jsonForecast;
    JsonChannel jsonChannel;
    JsonPlace jsonPlace;
    JsonResults jsonResults;
    JsonQuery jsonQuery;


    @BeforeClass
    public void Initialize(){
        jsonWind = new JsonWind();
        jsonWind.setChill(10);
        jsonWind.setDirection(10);
        jsonWind.setSpeed(10);
        jsonAtmosphere = new JsonAtmosphere();
        jsonAtmosphere.setPressure(10);
        jsonAtmosphere.setHumidity(10);
        jsonAtmosphere.setRising(0);
        jsonAtmosphere.setVisibility(10);
        jsonAstronomy = new JsonAstronomy();
        jsonAstronomy.setSunset("7:6 pm");
        jsonAstronomy.setSunrise("8:04 AM");
        jsonLocation = new JsonLocation();
        jsonLocation.setCity("asd");
        jsonLocation.setRegion("aSd2");
        jsonLocation.setCountry("sd3");
        jsonItem = new JsonItem();
        jsonCondition = new JsonCondition();
        jsonCondition.setDate("Sun, 28 May 2017 12:00 PM AKDT");
        jsonCondition.setTemp(10);
        jsonCondition.setCode(10);
        jsonCondition.setText("asd");
        jsonItem.setCondition(jsonCondition);
        List<JsonForecast> forecasts = new LinkedList<>();
        jsonForecast = new JsonForecast();
        jsonForecast.setDate("30 May 2017");
        jsonForecast.setCode(10);
        jsonForecast.setHigh(10);
        jsonForecast.setLow(01);
        jsonForecast.setText("ad");
        forecasts.add(jsonForecast);
        forecasts.add(jsonForecast);
        jsonItem.setForecast(forecasts);
        jsonChannel = new JsonChannel();
        jsonChannel.setAstronomy(jsonAstronomy);
        jsonChannel.setWind(jsonWind);
        jsonChannel.setAtmosphere(jsonAtmosphere);
        jsonChannel.setItem(jsonItem);
        jsonChannel.setLastBuildDate("Sun, 28 May 2017 01:46 PM AKDT");
        jsonChannel.setLocation(jsonLocation);
        jsonPlace = new JsonPlace();
        jsonPlace.setWoeid(1111);
        jsonResults = new JsonResults();
        jsonResults.setPlace(jsonPlace);
        jsonResults.setChannel(jsonChannel);
        jsonQuery = new JsonQuery();
        jsonQuery.setResults(jsonResults);
        jsonResponse = new JsonResponse();
        jsonResponse.setQuery(jsonQuery);
    }

    @Test
    public void testTransformJsonResponseToLocation(){
        Location l = Transformer.transformJsonResponseToLocation(jsonResponse);
        assertEquals(jsonLocation, l.getCountry());
        assertEquals(jsonLocation, l.getZone());
        assertEquals(jsonLocation, l.getCity());
        assertEquals(jsonLocation, l.getLastUpdate());



    }


}
