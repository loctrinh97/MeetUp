package com.example.meetup.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventResponse {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("response.events")
    @Expose
    List<EventGetFromApi> events;

    public List<EventGetFromApi> getEvents() {
        return events;
    }

    public void setEvents(List<EventGetFromApi> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "EventResponse{" +
                "events=" + events +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
