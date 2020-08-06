package com.example.meetup.model.response;

import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.dataLocal.News;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("response.categories")
    @Expose
    List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setNews(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Response{" +
                "categories=" + categories +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
