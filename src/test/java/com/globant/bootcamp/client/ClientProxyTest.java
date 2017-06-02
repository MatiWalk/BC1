package com.globant.bootcamp.client;

import com.globant.bootcamp.jsonDTO.*;
import org.easymock.EasyMock;
import org.junit.Test;

import java.net.NoRouteToHostException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mati on 01/06/2017.
 */
public class ClientProxyTest {

    @Test
    public void getDataTest() throws NoRouteToHostException {
        JsonAstronomy jsonAstronomy= new JsonAstronomy();
        jsonAstronomy.setSunrise("8:3 AM");
        jsonAstronomy.setSunset("9:43 PM");
        JsonChannel jsonChannel = new JsonChannel();
        jsonChannel.setAstronomy(jsonAstronomy);
        JsonResults jsonResults = new JsonResults();
        jsonResults.setChannel(jsonChannel);
        JsonQuery jsonQuery = new JsonQuery();
        jsonQuery.setResults(jsonResults);
        JsonResponse jsonResponseExpected = new JsonResponse();
        jsonResponseExpected.setQuery(jsonQuery);
        YahooWeatherClient yahooWeatherClientMock = EasyMock.createMock(YahooWeatherClient.class);
        EasyMock.expect(yahooWeatherClientMock.getData(EasyMock.anyString(), EasyMock.anyString())).andReturn(jsonResponseExpected);
        EasyMock.replay(yahooWeatherClientMock);
        YahooWeatherClient clientProxy = new ClientProxy(yahooWeatherClientMock);
        JsonResponse jsonResponse = clientProxy.getData("asd", "asd");
        assertEquals(jsonResponseExpected, jsonResponse);
        EasyMock.verify(yahooWeatherClientMock);

    }
}
