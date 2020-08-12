package com.example.meetup.model.response;

import com.example.meetup.model.dataLocal.News;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewResponse {
    @SerializedName("status")
    @Expose
    private int status;
   @SerializedName("response")
    @Expose
   private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "response=" + response +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public static class Response{
        public List<News> getNews;
        @SerializedName("news")
        @Expose
        List<News> news;

        public List<News> getNews() {
            return news;
        }

        public void setNews(List<News> news) {
            this.news = news;
        }
    }
}
