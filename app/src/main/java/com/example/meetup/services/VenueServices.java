package com.example.meetup.services;

import com.example.meetup.model.response.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

interface VenueServices {
    @GET("listNearlyEvents")
    Call<EventResponse> getListNearlyEvents(
            @Header("token") String token,
            @Query("radius") double radius,
            @Query("longitue") double longitue,
            @Query("latitude") double latitude
            );
}
