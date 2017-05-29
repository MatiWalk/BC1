package com.globant.bootcamp.jsonDTO;

/**
 * Created by Mati on 28/05/2017.
 */
public class JsonQuery {

    private int count;
    private String created;
    private String lang;
    private JsonResults results;

    public JsonQuery() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public JsonResults getResults() {
        return results;
    }

    public void setResults(JsonResults result) {
        this.results = result;
    }


    @Override
    public String toString() {
        return "com.globant.bootcamp.jsonDTO.JsonQuery{" +
                "count=" + count +
                ", created='" + created + '\'' +
                ", lang='" + lang + '\'' +
                ", result=" + results +
                '}';
    }

}
