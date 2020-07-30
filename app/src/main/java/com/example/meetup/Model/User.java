package com.example.meetup.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("response")
    @Expose
    private UserResponse response;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserResponse getResponse() {
        return response;
    }

    public void setResponse(UserResponse response) {
        this.response = response;
    }
}
