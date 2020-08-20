package com.example.meetup.services;

import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.response.CategoryResponse;
import com.example.meetup.model.response.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoryService {
    @GET("listCategories")
    Call<CategoryResponse> getListCategories();


}
