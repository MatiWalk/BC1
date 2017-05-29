package com.globant.bootcamp.transformers;

import com.globant.bootcamp.builder.*;
import com.globant.bootcamp.jsonDTO.*;
import com.globant.bootcamp.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mati on 28/05/2017.
 */
public class Transformer {
    public static int transformJsonResponseFirstQueryToInt(JsonResponse jsonResponse){
        return jsonResponse.getQuery().getResults().getPlace().getWoeid();
    }

    public static Location transformJsonResponseToLocation(JsonResponse jsonResponse){
       return LocationBuilder.builder()
               .withCountry(jsonResponse.getQuery().getResults().getChannel().getLocation().getCountry().trim())
               .withZone(jsonResponse.getQuery().getResults().getChannel().getLocation().getRegion().trim())
               .withCity(jsonResponse.getQuery().getResults().getChannel().getLocation().getCity().trim())
               .withToday(transformJsonChannelToToday(jsonResponse.getQuery().getResults().getChannel()))
               .withForecasts(transformJsonItemToForecastsList(jsonResponse.getQuery().getResults().getChannel().getItem()))
               .withLastUpdate(LocalDateTime.parse(jsonResponse.getQuery().getResults().getChannel().getLastBuildDate(), DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a z", Locale.US)))
               .build();
    }

    public static Astronomy transformJsonAstronomyToAstronomy(JsonAstronomy jsonAstronomy){
        return AstronomyBuilder.builder()
                .withSunrise(LocalTime.parse(jsonAstronomy.getSunrise(), DateTimeFormatter.ofPattern("K:mm a")))
                .withSunset(LocalTime.parse(jsonAstronomy.getSunset(), DateTimeFormatter.ofPattern("K:mm a")))
                .build();

    }

    public static Atmosphere transformJsonAtmosphereToAtmosphere(JsonAtmosphere jsonAtmosphere){
        return AtmosphereBuilder.builder()
                .withHumidity(jsonAtmosphere.getHumidity())
                .withPressure(jsonAtmosphere.getPressure())
                .withRising(barometricPressure.values()[jsonAtmosphere.getRising()])
                .withVisibility(jsonAtmosphere.getVisibility())
                .build();
    }

    public static List<Forecast> transformJsonItemToForecastsList(JsonItem jsonItem){
        List<Forecast> forecasts = new LinkedList<>();
        for (JsonForecast jsonForecast:jsonItem.getForecast()) {
            Forecast forecast = ForecastBuilder.builder()
                    .withForecastWeather(transformJsonConditionOrForecastToWeatherCode(jsonForecast))
                    .withDate(LocalDate.parse(jsonForecast.getDate(), DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.US)))
                    .withHighTemperature(jsonForecast.getHigh())
                    .withLowTemperature(jsonForecast.getLow())
                    .build();
            forecasts.add(forecast);
        }
        return forecasts;


    }

    public static Today transformJsonChannelToToday(JsonChannel jsonChannel){
        return TodayBuilder.builder()
                .withDate(LocalDate.parse(jsonChannel.getItem().getCondition().getDate(), DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a z", Locale.US)))
                .withCurrentWeather(transformJsonConditionOrForecastToWeatherCode(jsonChannel.getItem().getCondition()))
                .withCurrentTemperature(jsonChannel.getItem().getCondition().getTemp())
                .withAstronomy(transformJsonAstronomyToAstronomy(jsonChannel.getAstronomy()))
                .withAtmosphere(transformJsonAtmosphereToAtmosphere(jsonChannel.getAtmosphere()))
                .withWind(transformJsonWindToWind(jsonChannel.getWind()))
                .build();
    }

    public static WeatherCode transformJsonConditionOrForecastToWeatherCode(JsonWeatherCode jsonWeatherCodeode){
        return WeatherCodeBuilder.builder()
                .withCode(jsonWeatherCodeode.getCode())
                .withWeather(jsonWeatherCodeode.getText())
                .build();
    }

    public static Wind transformJsonWindToWind(JsonWind jsonWind){
        return WindBuilder.builder()
                .withChill(jsonWind.getChill())
                .withDirection(jsonWind.getDirection())
                .withSpeed(jsonWind.getSpeed())
                .build();
    }
}
