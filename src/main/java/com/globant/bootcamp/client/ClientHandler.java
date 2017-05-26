package com.globant.bootcamp.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.bootcamp.builder.*;
import com.globant.bootcamp.model.*;
import com.globant.bootcamp.persistence.ClimateCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mati on 24/05/2017.
 */
@Component
public class ClientHandler {


    @Resource
    private YahooWeatherClient yahooWeatherClient;
    @Autowired
    private ClimateCRUD<Location> locationClimateCRUD;
    @Autowired
    private ClimateCRUD<Today> todayClimateCRUD;
    @Autowired
    private ClimateCRUD<Forecast> forecastClimateCRUD;

    private ObjectMapper mapper;

    int amountForecast = 10;

    public Location getData(String country, String zone, String city){
        mapper = new ObjectMapper();
        Location locationInput = LocationBuilder.builder()
                .withCountry(country)
                .withZone(zone)
                .withCity(city)
                .build();
        Location locationResult = null;
        boolean noConnection = false;
        try {
            //gets woeid from yahoo
            String selectLocation = "select woeid from geo.places(1) where text=\" "+locationInput.getCity()+
                    ", " + locationInput.getZone() + ", " + locationInput.getCountry() + "\"";

            JsonNode locationWoeid = mapper.readTree(yahooWeatherClient.getConditions(selectLocation, "json"));
            int woeid = locationWoeid.get("query").get("results").get("place").get("woeid").asInt();

            //gets all the other data
            String selectWeather = "select * from weather.forecast where woeid = "+ woeid;
            JsonNode jsonResponse = mapper.readTree(yahooWeatherClient.getConditions(selectWeather, "json"));
            JsonNode resultJson = jsonResponse.get("query").get("results").get("channel");
            JsonNode locationJson = resultJson.get("location");
            JsonNode astronomyJson = resultJson.get("astronomy");
            JsonNode atmosphereJson = resultJson.get("atmosphere");
            JsonNode windJson = resultJson.get("wind");
            JsonNode todayJson = resultJson.get("item").get("condition");
            JsonNode forecastsJson = resultJson.get("item").get("forecast");

            List<Forecast> forecasts = new LinkedList<>();
            for (int i = 0; i < amountForecast; i++){
                JsonNode singleForecastJson = forecastsJson.get(i);
                Forecast forecast = ForecastBuilder.builder()
                    .withWOEID(woeid)
                    .withDate(LocalDate.parse(singleForecastJson.get("date").asText(), DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.US)))
                    .withForecastWeather(WeatherCodeBuilder.builder()
                        .withCode(singleForecastJson.get("code").asInt())
                        .withWeather(todayJson.get("text").asText())
                        .build())
                    .withHighTemperature(singleForecastJson.get("high").asInt())
                    .withLowTemperature(singleForecastJson.get("low").asInt())
                    .build();
                forecasts.add(forecast);
            }

            locationResult = LocationBuilder.builder()
                .withWoeid(woeid)
                .withCountry(locationJson.get("country").asText().trim())
                .withZone(locationJson.get("region").asText().trim())
                .withCity(locationJson.get("city").asText().trim())
                .withToday(TodayBuilder.builder()
                    .withWOEID(woeid)
                    .withDate(LocalDate.parse(todayJson.get("date").asText(), DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a z", Locale.US)))
                    .withCurrentWeather(WeatherCodeBuilder.builder()
                        .withCode(todayJson.get("code").asInt())
                        .withWeather(todayJson.get("text").asText())
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
                        .build())
                .withForecasts(forecasts)
                .withLastUpdate(LocalDateTime.parse(resultJson.get("item").get("pubDate").asText(), DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a z", Locale.US)))
                .build();


            if (locationClimateCRUD.selectByID(locationResult.getWoeid()) == null){
                locationClimateCRUD.insert(locationResult);
            }

            if (todayClimateCRUD.selectByObject(locationResult.getToday()) == null){
                todayClimateCRUD.insert(locationResult.getToday());
            }else
            {
                todayClimateCRUD.update(locationResult.getToday());
            }

            for (Forecast f: locationResult.getForecasts()) {
                if (forecastClimateCRUD.selectByObject(f) == null){
                    forecastClimateCRUD.insert(f);
                }else
                {
                    forecastClimateCRUD.update(f);
                }
            }

        return locationResult;
        } catch (Exception e) {
            System.out.println("Error retrieving from client");
            e.printStackTrace();
            noConnection = true;
        }

        if (noConnection){
            locationResult = locationClimateCRUD.selectByObject(LocationBuilder.builder().withCountry(country).withCity(city).withZone(zone).build());
            if (locationResult==null) return null;
            Today today = todayClimateCRUD.selectByObject(TodayBuilder.builder().withDate(LocalDate.now()).withWOEID(locationResult.getWoeid()).build());
            if (today==null) return null;
            locationResult.setToday(today);
            List<Forecast> forecasts = new LinkedList<>();
            for (int i = 0; i < amountForecast; i++){
                Forecast forecast = forecastClimateCRUD.selectByObject(ForecastBuilder.builder().withDate(LocalDate.now().plusDays(i)).withWOEID(locationResult.getWoeid()).build());
                forecasts.add(forecast);
            }
            locationResult.setForecasts(forecasts);
        }
        return locationResult;
    }


}
