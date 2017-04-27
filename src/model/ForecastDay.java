package model;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * Created by Mati on 27/04/2017.
 */
public class ForecastDay extends Day {

    DayOfWeek day;
    float high;
    float low;

    public ForecastDay() {
    }

    public ForecastDay(int code, LocalDateTime date, String text, float high, float low) {
        super(code, date, text);
        this.day = date.getDayOfWeek();
        this.high = high;
        this.low = low;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    @Override
    public void setDate(LocalDateTime date) {
        super.setDate(date);
        day = date.getDayOfWeek();
    }

    public DayOfWeek getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "ForecastDay{" +
                "code=" + code +
                ", day=" + day +
                ", date=" + date +
                ", high=" + high +
                ", text='" + text + '\'' +
                ", low=" + low +
                '}';
    }
}
