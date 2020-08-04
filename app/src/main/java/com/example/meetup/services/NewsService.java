package com.example.meetup.services;

import com.example.meetup.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    @GET("/listNews")
    Call<NewsResponse> getListNews();
}
