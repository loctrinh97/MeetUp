package com.example.meetup.services;

import com.example.meetup.model.response.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventService {
    @GET("listPopularEvents")
    Call<EventResponse> getListPopularEvents();
    @GET("listEventsByCategory")
    Call<EventResponse> getListEventsByCategory(
            @Query("category_id") int category_id
    );

}
