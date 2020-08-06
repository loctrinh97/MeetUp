package com.example.meetup.services;

import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
    @GET("listCategories")
    Call<CategoryResponse> getListCategories();

}
