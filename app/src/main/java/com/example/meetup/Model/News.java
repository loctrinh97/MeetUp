package com.example.meetup.Model;

public class News {
    int id;
    String thumb;
    String publish_date;
    String title;
    String author;
    String feed;
    public News(int id, String thumb, String publish_date, String title, String author, String feed){
        this.id = id;
        this.author=author;
        this.title = title;
        this.thumb = thumb;
        this.publish_date= publish_date;
        this.feed = feed;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getFeed() {
        return feed;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public String getThumb() {
        return thumb;
    }

    public String getTitle() {
        return title;
    }
}
