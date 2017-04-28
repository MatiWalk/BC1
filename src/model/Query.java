package model;

import java.time.LocalDateTime;

/**
 * Created by Mati on 28/04/2017.
 */
public class Query {

    int count;
    LocalDateTime created;
    String lang;
    Result result;

    public Query() {
    }

    public Query(int count, LocalDateTime created, String lang, Result result) {
        this.count = count;
        this.created = created;
        this.lang = lang;
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String toString() {
        return "Query{" +
                "count=" + count +
                ", created=" + created +
                ", lang='" + lang + '\'' +
                ", result=" + result +
                '}';
    }
}
