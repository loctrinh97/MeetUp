package com.example.meetup.services;

import com.example.meetup.model.response.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    // register service
    @POST("register")
    Call<User> signUp(
            @Query("name") String name,
            @Query("email") String email,
            @Query("password") String password
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
