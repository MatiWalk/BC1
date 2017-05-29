package com.globant.bootcamp.jsonDTO;

/**
 * Created by Mati on 28/05/2017.
 */
public class JsonResults {
    private JsonChannel Channel;
    private JsonPlace place;

    public JsonResults() {
    }

    public JsonChannel getChannel() {
        return Channel;
    }

    public void setChannel(JsonChannel jsonChannel) {
        this.Channel = jsonChannel;
    }

    public JsonPlace getPlace() {
        return place;
    }

    public void setPlace(JsonPlace place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "com.globant.bootcamp.jsonDTO.JsonResults{" +
                "jsonChannel=" + Channel +
                '}';
    }
}
