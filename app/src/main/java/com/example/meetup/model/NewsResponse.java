package com.example.meetup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @SerializedName("status")
    @Expose
    private long status;

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
        return "NewsResponse{" +
                "status=" + status +
                ", response=" + response +
                '}';
    }

    public void setStatus(long status) {
        this.status = status;
    }


    public long getStatus() {
        return status;
    }

}
