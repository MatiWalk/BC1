package com.globant.bootcamp.jsonDTO;

/**
 * Created by Mati on 28/05/2017.
 */
public class JsonResponse {

    private JsonQuery jsonQuery;

    public JsonResponse() {
    }

    public JsonQuery getQuery() {
        return jsonQuery;
    }

    public void setQuery(JsonQuery jsonQuery) {
        this.jsonQuery = jsonQuery;
    }

    @Override
    public String toString() {
        return "com.globant.bootcamp.jsonDTO.JsonResponse{" +
                "jsonQuery=" + jsonQuery +
                '}';
    }
}
