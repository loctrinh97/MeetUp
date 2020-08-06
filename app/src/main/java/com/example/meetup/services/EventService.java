package com.example.meetup.services;

import com.example.meetup.model.response.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventService {
    @GET("listPopularEvents")
    Call<EventResponse> getListPopularEvents();
}
