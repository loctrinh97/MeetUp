package com.example.meetup.services;

import com.example.meetup.model.response.CategoryResponse;
import com.example.meetup.model.response.EventResponse;
import com.example.meetup.model.response.NewResponse;
import com.example.meetup.model.response.ResultUpdateResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BaseService {
    @GET("listCategories")
    Call<CategoryResponse> getListCategories();

    @GET("listMyEvents")
    Call<EventResponse> getListMyEventsJoined(
            @Query("token") String token,
            @Query("status") long status
    );

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

    @GET("listNews")
    Call<NewResponse> getListNews();
    @GET("listNews")
    Call<NewResponse> getListNewsFirst(
            @Query("pageIndex") int pageIndex,
            @Query("pageSize")  int pageSize
    );

    @GET("listNearlyEvents")
    Call<EventResponse> getListNearlyEvents(
            @Header("token") String token,
            @Query("radius") double radius,
            @Query("longitue") double longitue,
            @Query("latitude") double latitude
    );
}
