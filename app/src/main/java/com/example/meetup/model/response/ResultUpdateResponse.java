package com.example.meetup.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultUpdateResponse {
    @Expose
    public int status ;
    @Expose
    @SerializedName("error_code")
    public String errorCode;
    @Expose
    @SerializedName("error_message")
    public String errorMessage;

    @Override
    public String toString() {
        return "ResultUpdateResponse{" +
                "status=" + status +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
