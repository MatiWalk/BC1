package com.globant.bootcamp.client;

import com.globant.bootcamp.model.Location;

/**
 * Created by Mati on 28/05/2017.
 */
public class FormatHelper {
    
    public static Location standarizeLocationStrings(Location location){
        location.setCountry(capitalizeFirstLetter(location.getCountry()));
        location.setZone(location.getZone().toUpperCase());
        location.setCity(capitalizeFirstLetter(location.getCity()));
        return location;
    }



    private static String capitalizeFirstLetter(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
