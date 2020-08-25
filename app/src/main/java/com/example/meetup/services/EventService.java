package com.example.meetup.services;

import com.example.meetup.model.response.EventResponse;
import com.example.meetup.model.response.ResultUpdateResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EventService {
    @GET("listPopularEvents")
    Call<EventResponse> getListPopularEvents();
    @GET("listEventsByCategory")
    Call<EventResponse> getListEventsByCategory(
            @Query("category_id") int category_id
    );
    @POST("doUpdateEvent")
    Call<ResultUpdateResponse> doUpdateEvent(
            @Query("token") String token,
            @Query("status") int status,
            @Query("event_id") int eventId
    );
}
