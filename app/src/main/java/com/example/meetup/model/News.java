package com.example.meetup.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.meetup.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "news")
public class News {
    @SerializedName("id")
    @Expose
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "new_id")
    private long newId;
    @SerializedName("thumb_img")
    @Expose
    @ColumnInfo(name = "thumb")
    private String thumb;
    @SerializedName("publish_date")
    @Expose
    @ColumnInfo(name = "publish_date")
    private String publishDate;
    @Expose
    @ColumnInfo(name = "title")
    private String title;
    @Expose
    @ColumnInfo(name = "author")
    private String author;
    @Expose
    @ColumnInfo(name = "feed")
    private String feed;
    @SerializedName("detail_url")
    @Expose
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

    @Ignore
    public News( String thumb, String publish_date, String title, String author, String feed,String detail_url){
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


    @NotNull
    @Override
    public String toString() {
        return "News{" +
                "newId=" + newId +
                ", thumb='" + thumb + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", feed='" + feed + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                '}';
    }
}
