package com.example.meetup.services;

import com.example.meetup.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    // register service
    @FormUrlEncoded
    @POST("register")
    Call<User> signUp(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );


    @POST("login")
    Call<User> login(
            @Query("email") String email,
            @Query("password") String password
    );


    @POST("resetPassword")
    Call<User> resetPassword(
            @Query("email") String email
    );
}
