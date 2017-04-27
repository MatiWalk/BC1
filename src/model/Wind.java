package model;

/**
 * Information about the wind
 * Created by Mati on 26/04/2017.
 */
public class Wind {

    int chill;
    int direction;
    int speed;

    public Wind(){
        chill = 0;
        direction = 0;
        speed = 0;
    }

    public Wind(int chill, int direction, int speed) {
        this.chill = chill;
        this.direction = direction;
        this.speed = speed;
    }

    public int getChill() {
        return chill;
    }

    public void setChill(int chill) {
        this.chill = chill;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String toString() {
        return "Wind{" +
                "chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}
