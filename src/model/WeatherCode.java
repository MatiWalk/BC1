package model;

/**
 * Created by Mati on 01/05/2017.
 */
public class WeatherCode {

    int code;
    String text;

    public WeatherCode() {
    }

    public WeatherCode(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "WeatherCode{" +
                "weatherCode=" + code +
                ", text='" + text + '\'' +
                '}';
    }
}
