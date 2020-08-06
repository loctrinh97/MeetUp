package com.example.meetup.model.dataLocal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "events_categories")
public class EventsCategories {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "event_id")
    private int eventID;
    @ColumnInfo(name = "category_id")
    private int categoryId;
    public EventsCategories(){

    }
    @Ignore
    public EventsCategories(int eventID, int categoryId) {
        this.eventID = eventID;
        this.categoryId = categoryId;
    }
    @Ignore
    public EventsCategories(int id, int eventID, int categoryId) {
        this.id = id;
        this.eventID = eventID;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
