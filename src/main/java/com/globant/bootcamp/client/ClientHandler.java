package com.globant.bootcamp.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.bootcamp.builder.*;
import com.globant.bootcamp.model.Atmosphere;
import com.globant.bootcamp.model.Location;
import com.globant.bootcamp.model.Today;
import com.globant.bootcamp.model.barometricPressure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Mati on 24/05/2017.
 */
@Component
public class ClientHandler {


    @Resource
    private YahooWeatherClient yahooWeatherClient;


    private ObjectMapper mapper;

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Location getData(){
        Location l = null;
        mapper = new ObjectMapper();
        try {

            JsonNode jsonResponse = mapper.readTree(yahooWeatherClient.getConditions("select * from weather.forecast where woeid in" +
                    " (select woeid from geo.places(1) where text=\"nome, ak\")", "json"));
            JsonNode resultJson = jsonResponse.get("query").get("results").get("channel");
            JsonNode locationJson = resultJson.get("location");
            JsonNode astronomyJson = resultJson.get("astronomy");
            JsonNode atmosphereJson = resultJson.get("atmosphere");
            JsonNode windJson = resultJson.get("wind");
            JsonNode todayJson = resultJson.get("item").get("condition");




            Today today = TodayBuilder.builder()
                    .withDate(LocalDate.parse(todayJson.get("date").asText(), DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a z", Locale.US)))
                    .withCurrentWeather(WeatherCodeBuilder.builder()
                            .withCode(todayJson.get("code").asInt())
                            .build())
                    .withCurrentTemperature(todayJson.get("temp").asInt())
                    .withAstronomy(AstronomyBuilder.builder()
                            .withSunrise(LocalTime.parse(astronomyJson.get("sunrise").asText().toUpperCase(), DateTimeFormatter.ofPattern("K:mm a") ))
                            .withSunset(LocalTime.parse(astronomyJson.get("sunset").asText().toUpperCase(), DateTimeFormatter.ofPattern("K:mm a")))
                            .build())
                    .withAtmosphere(AtmosphereBuilder.builder()
                            .withHumidity(atmosphereJson.get("humidity").asInt())
                            .withPressure(((float) atmosphereJson.get("pressure").asDouble()))
                            .withRising(barometricPressure.values()[atmosphereJson.get("rising").asInt()])
                            .withVisibility((float) atmosphereJson.get("visibility").asDouble())
                            .build())
                    .withWind(WindBuilder.builder()
                            .withChill(windJson.get("chill").asInt())
                            .withDirection(windJson.get("direction").asInt())
                            .withSpeed(windJson.get("speed").asInt())
                            .build())
                    .build();
            System.out.println(today.toString());
            l = LocationBuilder.builder().withToday(today).build();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return l;
    }


}
