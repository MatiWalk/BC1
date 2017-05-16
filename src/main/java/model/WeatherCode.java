package model;

/**
 * Created by Mati on 01/05/2017.
 */
public class WeatherCode {

    int code;
    String text;

    private WeatherCode(WeatherCodeBuilder weatherCodeBuilder) {
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
        return "model.WeatherCode{" +
                "code=" + code +
                ", text='" + text + '\'' +
                '}';
    }

    public static class WeatherCodeBuilder{

        int code;
        String text;

        public WeatherCodeBuilder withCode(int code){
            this.code = code;
            return this;
        }

        public WeatherCodeBuilder withString(String text){
            this.text = text;
            return this;
        }

        public WeatherCode build(){
            return new WeatherCode(this);
        }
    }
}
