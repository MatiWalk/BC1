package com.globant.bootcamp.client;

import com.globant.bootcamp.builder.ForecastBuilder;
import com.globant.bootcamp.builder.TodayBuilder;
import com.globant.bootcamp.jsonDTO.JsonResponse;
import com.globant.bootcamp.model.Forecast;
import com.globant.bootcamp.model.Location;
import com.globant.bootcamp.model.Today;
import com.globant.bootcamp.persistence.ClimateCRUD;
import com.globant.bootcamp.transformers.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mati on 29/05/2017.
 */
@Component
public class AdapterClientToParser implements YahooWeatherParser{

    @Autowired
    YahooWeatherClient clientProxy;
    @Autowired
    private ClimateCRUD<Location> locationClimateCRUD;
    @Autowired
    private ClimateCRUD<Today> todayClimateCRUD;
    @Autowired
    private ClimateCRUD<Forecast> forecastClimateCRUD;

    private int amountForecast = 10;

    public AdapterClientToParser(YahooWeatherClient clientProxy) {
        this.clientProxy = clientProxy;
    }

    @Override
    public Location getData(Location locationInput) {

        locationInput = FormatHelper.standarizeLocationStrings(locationInput);
        Location locationResult = null;
        boolean isConnected = false;


        try {
            //gets woeid from yahoo
            String selectLocation = "select woeid from geo.places(1) where text=\" "+locationInput.getCity()+
                    ", " + locationInput.getZone() + ", " + locationInput.getCountry() + "\"";

            int woeid = Transformer.transformJsonResponseFirstQueryToInt(clientProxy.getData(selectLocation, "json"));

            //gets all the other data
            String selectWeather = "select * from weather.forecast where woeid = "+ woeid;
            JsonResponse jsonResponse = clientProxy.getData(selectWeather, "json");
            locationResult = Transformer.transformJsonResponseToLocation(jsonResponse);
            FormatHelper.addWoeidToObjects(locationResult, woeid);

            if (locationClimateCRUD.selectByID(locationResult.getWoeid()) == null){
                locationClimateCRUD.insert(locationResult);
            }else{
                locationClimateCRUD.update(locationResult);
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

            isConnected = true;
        }catch (Exception e){
        }

        if (!isConnected){
            locationResult = locationClimateCRUD.selectByObject(locationInput);
            if (locationResult==null) return null;
            Today today = todayClimateCRUD.selectByObject(TodayBuilder.builder().withDate(LocalDate.now()).withWOEID(locationResult.getWoeid()).build());

            locationResult.setToday(today);
            List<Forecast> forecasts = new LinkedList<>();
            for (int i = 0; i < amountForecast; i++){
                Forecast forecast = forecastClimateCRUD.selectByObject(ForecastBuilder.builder().withDate(LocalDate.now().plusDays(i)).withWOEID(locationResult.getWoeid()).build());
                if (forecast != null)forecasts.add(forecast);
            }
            locationResult.setForecasts(forecasts);
        }
        return locationResult;

    }
}
