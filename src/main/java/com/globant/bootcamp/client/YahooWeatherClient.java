package com.globant.bootcamp.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by Mati on 24/05/2017.
 */

@Path("/v1/public")
@Produces("application/json")
public interface YahooWeatherClient {
    @GET
    @Path("/yql")
    String getConditions(@QueryParam("q")String query, @QueryParam("format")String format);
}
