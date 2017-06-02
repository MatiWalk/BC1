package com.globant.bootcamp.client;

import com.globant.bootcamp.model.Location;

/**
 * Created by Mati on 29/05/2017.
 */
public interface YahooWeatherParser {
    Location getData(Location locationInput);
}
