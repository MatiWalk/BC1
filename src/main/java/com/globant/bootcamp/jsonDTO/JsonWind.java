package com.globant.bootcamp.jsonDTO;

/**
 * Created by Mati on 28/05/2017.
 */
public class JsonWind {
    private int chill;
    private int direction;
    private int speed;

    public JsonWind() {
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

    @Override
    public String toString() {
        return "com.globant.bootcamp.jsonDTO.JsonWind{" +
                "chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}
