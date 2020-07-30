package com.example.meetup.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import com.bumptech.glide.Glide;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "news")
public class News {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "new_id")
    private long new_id;
    @ColumnInfo(name = "thumb")
    private String thumb;
    @ColumnInfo(name = "publish_date")
    private String publish_date;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "author")
    private String author;
    @ColumnInfo(name = "feed")
    private String feed;
    @ColumnInfo(name = "detail_url")
    private String detail_url;
    public News(){

    }
    @Ignore
    public News(int id, String thumb, String publish_date, String title, String author, String feed,String detail_url){
        this.new_id= id;
        this.author=author;
        this.title = title;
        this.thumb = thumb;
        this.publish_date= publish_date;
        this.feed = feed;
        this.detail_url = detail_url;
    }

    public long getNew_id() {
        return new_id;
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

    public String getDetail_url() {
        return detail_url;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public void setNew_id(long new_id) {
        this.new_id = new_id;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(final ImageView view, final String imageUrl){
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
