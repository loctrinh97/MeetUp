package com.example.meetup.model.response;

import com.example.meetup.model.dataLocal.Category;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("response")
    @Expose
    private Response response;

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "status=" + status +
                ", response=" + response +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public static class Response{
        @SerializedName("categories")
        @Expose
        private List<Category> categories;
        public List<Category> getCategories() {
            return categories;
        }

        public void setNews(List<Category> categories) {
            this.categories = categories;
        }

    }
}
