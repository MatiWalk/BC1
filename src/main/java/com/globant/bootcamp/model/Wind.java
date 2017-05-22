package com.globant.bootcamp.model;

/**
 * Information about the wind
 * Created by Mati on 26/04/2017.
 */
public class Wind {

    int id;
    private int chill;
    private int direction;
    private int speed;

    public Wind() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "com.globant.bootcamp.model.Wind{" +
                "id=" + id +
                ", chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}
