package model;

import javafx.stage.WindowBuilder;

/**
 * Information about the wind
 * Created by Mati on 26/04/2017.
 */
public class Wind {

    int chill;
    int direction;
    int speed;

    public Wind(WindBuilder windBuilder) {
        this.chill = windBuilder.chill;
        this.direction = windBuilder.direction;
        this.speed = windBuilder.speed;
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
        return "model.Wind{" +
                "chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }

    public static class WindBuilder{

        int chill;
        int direction;
        int speed;

        public WindBuilder withChill (int chill){
            this.chill = chill;
            return this;
        }

        public WindBuilder withDirection (int direction){
            this.direction = direction;
            return this;
        }

        public WindBuilder withSpeed (int speed){
            this.speed = speed;
            return this;
        }

        public Wind build(){
            return new Wind(this);
        }
    }
}
