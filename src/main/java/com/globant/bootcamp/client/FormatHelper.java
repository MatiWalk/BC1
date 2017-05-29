package com.globant.bootcamp.client;

import com.globant.bootcamp.jsonDTO.JsonAstronomy;
import com.globant.bootcamp.model.Forecast;
import com.globant.bootcamp.model.Location;

/**
 * Created by Mati on 28/05/2017.
 */
public class FormatHelper {
    
    public static Location standarizeLocationStrings(Location location){
        location.setCountry(capitalizeFirstLetter(location.getCountry()).replaceAll("-", " ").trim());
        location.setZone(location.getZone().toUpperCase().trim());
        location.setCity(capitalizeFirstLetter(location.getCity()).replaceAll("-", " ").trim());
        return location;
    }

    public static JsonAstronomy fixJsonAstronomyFormatError(JsonAstronomy jsonAstronomy){
        jsonAstronomy.setSunrise(jsonAstronomy.getSunrise().toUpperCase());
        jsonAstronomy.setSunset(jsonAstronomy.getSunset().toUpperCase());
        if(jsonAstronomy.getSunrise().length() == 6) jsonAstronomy.setSunrise(jsonAstronomy.getSunrise().substring(0, 2) + "0" + jsonAstronomy.getSunrise().substring(2));
        return jsonAstronomy;
    }

    private static String capitalizeFirstLetter(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static Location addWoeidToObjects(Location location, int woeid){
        location.setWoeid(woeid);
        location.getToday().setWoeid(woeid);
        for (Forecast f: location.getForecasts()) {
            f.setWoeid(woeid);
        }
        return location;
    }
}
