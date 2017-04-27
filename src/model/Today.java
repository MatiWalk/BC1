package model;

import java.time.LocalDateTime;

/**
 * Created by Mati on 27/04/2017.
 */
public class Today extends Day {

    float temperature;

    public Today()  {
        super();
        temperature = 0f;
    }

    public Today(int code, LocalDateTime date, String text, float temperature) {
        super(code, date, text);
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Today{" +
                "temperature=" + temperature +
                ", code=" + code +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}
