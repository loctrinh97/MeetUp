package com.example.meetup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @SerializedName("status")
    @Expose
    private long status;

    @SerializedName("respone.news")
    @Expose
    private List<News> newsList;

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public long getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "NewsResponse{" +
                "status=" + status +
                ", newsList=" + newsList +
                '}';
    }
}
