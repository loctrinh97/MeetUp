package com.example.meetup.model.dataLocal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_events")
public class UsersEvents {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "event_id")
    private int eventId;
    @ColumnInfo(name = "user_id")
    private int userId;
    @ColumnInfo(name = "status")
    private int status;
    public UsersEvents(){

    }
    @Ignore
    public UsersEvents(int id, int eventId, int userId, int status) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.status = status;
    }
    @Ignore
    public UsersEvents(int eventId, int userId, int status) {
        this.eventId = eventId;
        this.userId = userId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
