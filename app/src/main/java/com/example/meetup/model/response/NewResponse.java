package com.example.meetup.model.response;

import com.example.meetup.model.dataLocal.News;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewResponse {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("response.news")
    @Expose
    List<News> news;

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "Response{" +
                "news=" + news +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
