package com.example.meetup.services;

import com.example.meetup.model.response.NewResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {
    @GET("listNews")
    Call<NewResponse> getListNews();
}
