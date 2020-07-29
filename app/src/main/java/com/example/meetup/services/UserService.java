package com.example.meetup.services;

import com.example.meetup.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
@POST("register")
Call<User> signUp(@Body User post);
    @FormUrlEncoded
    @POST("register")
    Call<User> signUp(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );
}
