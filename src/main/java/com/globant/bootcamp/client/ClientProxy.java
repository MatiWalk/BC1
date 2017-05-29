package com.globant.bootcamp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.bootcamp.builder.*;
import com.globant.bootcamp.jsonDTO.JsonResponse;
import com.globant.bootcamp.model.*;
import com.globant.bootcamp.persistence.ClimateCRUD;
import com.globant.bootcamp.transformers.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.NoRouteToHostException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mati on 24/05/2017.
 */
@Component
public class ClientProxy implements YahooWeatherClient {


    @Resource
    private YahooWeatherClient yahooWeatherClient;

    @Override
    public JsonResponse getData(String query, String format) throws NoRouteToHostException {
        JsonResponse jsonResponse = yahooWeatherClient.getData(query, format);
        if (jsonResponse.getQuery().getResults().getChannel() != null) jsonResponse.getQuery().getResults().getChannel().setAstronomy(FormatHelper.fixJsonAstronomyFormatError(jsonResponse.getQuery().getResults().getChannel().getAstronomy()));
        return jsonResponse;
    }
}
