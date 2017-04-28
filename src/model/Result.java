package model;

/**
 * Created by Mati on 28/04/2017.
 */
public class Result {

    Channel channel;

    public Result() {
    }

    public Result(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String toString() {
        return "Result{" +
                "channel=" + channel +
                '}';
    }
}
