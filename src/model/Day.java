package model;

import java.time.LocalDateTime;

/**
 * Created by Mati on 27/04/2017.
 */
abstract public class Day {

    protected int code;
    protected LocalDateTime date;
    protected String text;

    public Day (){
        code = 0;
        date = LocalDateTime.MIN;
        text = "";
    }

    public Day(int code, LocalDateTime date, String text) {
        this.code = code;
        this.date = date;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        text = text;
    }

    public String toString() {
        return "Day{" +
                "code=" + code +
                ", date=" + date +
                ", Text='" + text + '\'' +
                '}';
    }
}
