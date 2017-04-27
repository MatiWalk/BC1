package model;

/**
 * Created by Mati on 27/04/2017.
 */
public class Guid {

    boolean isPermaLink;

    public Guid() {
    }

    public Guid(boolean isPermaLink) {
        this.isPermaLink = isPermaLink;
    }

    public boolean isPermaLink() {
        return isPermaLink;
    }

    public void setPermaLink(boolean permaLink) {
        isPermaLink = permaLink;
    }

    @Override
    public String toString() {
        return "Guid{" +
                "isPermaLink=" + isPermaLink +
                '}';
    }
}
