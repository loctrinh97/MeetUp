package com.example.meetup.services;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.response.EventGetFromApi;
import com.example.meetup.model.response.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventJoinedServices {

    @GET("listMyEvents")
    Call<EventResponse> getListMyEventsJoined(
            @Query("token") String token,
            @Query("status") long status
    );
}
