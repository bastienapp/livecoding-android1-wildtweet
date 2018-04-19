package fr.wcs.wildtweet;

/**
 * Created by bastienwcs on 14/03/18.
 */

public class TweetModel {

    private String username;
    private String content;
    private long datetime;

    public TweetModel() {
    }

    public TweetModel(String username, String content, long datetime) {
        this.username = username;
        this.content = content;
        this.datetime = datetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
