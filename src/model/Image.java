package model;

/**
 * Created by Mati on 27/04/2017.
 */
public class Image {

    String title;
    int width;
    int height;
    String link;
    String url;

    public Image() {
        title = "";
        width = 0;
        height = 0;
        link = "";
        url = "";
    }

    public Image(String title, int width, int height, String link, String url) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.link = link;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return "Image{" +
                "title='" + title + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", link='" + link + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
