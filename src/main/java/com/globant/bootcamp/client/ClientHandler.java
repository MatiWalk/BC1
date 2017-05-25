package com.globant.bootcamp.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.bootcamp.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by Mati on 24/05/2017.
 */
@Component
public class ClientHandler {


    @Resource
    private YahooWeatherClient yahooWeatherClient;

    @Autowired
    private ObjectMapper mapper;

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Location getData(){
        Location l = null;
        try {

            JsonNode jsonResponse = mapper.readTree(yahooWeatherClient.getConditions("select * from weather.forecast where woeid in" +
                    " (select woeid from geo.places(1) where text=\"nome, ak\")", "json"));
            JsonNode resultJson = jsonResponse.get("query").get("results").get("channel");
            JsonNode locationJson = resultJson.get("location");
            JsonNode astronomyJson = resultJson.get("astronomy");
            JsonNode atmosphereJson = resultJson.get("atmosphere");
            JsonNode windJson = resultJson.get("wind");
            JsonNode todayJson = resultJson.get("item").get("condition");
            

        } catch (IOException e) {
            e.printStackTrace();
        }

        return l;
    }


}
