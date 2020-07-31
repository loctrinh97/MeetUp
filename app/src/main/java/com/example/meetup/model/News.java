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
    private long newId;
    @ColumnInfo(name = "thumb")
    private String thumb;
    @ColumnInfo(name = "publish_date")
    private String publishDate;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "author")
    private String author;
    @ColumnInfo(name = "feed")
    private String feed;
    @ColumnInfo(name = "detail_url")
    private String detailUrl;
    public News(){

    }
    @Ignore
    public News(int id, String thumb, String publish_date, String title, String author, String feed,String detail_url){
        this.newId = id;
        this.author=author;
        this.title = title;
        this.thumb = thumb;
        this.publishDate = publish_date;
        this.feed = feed;
        this.detailUrl = detail_url;
    }

    public long getNewId() {
        return newId;
    }

    public String getAuthor() {
        return author;
    }

    public String getFeed() {
        return feed;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getThumb() {
        return thumb;
    }

    public String getTitle() {
        return title;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public void setNewId(long newId) {
        this.newId = newId;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
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
