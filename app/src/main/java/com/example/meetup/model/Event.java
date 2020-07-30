package com.example.meetup.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "events")
public class Event {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "photo")
    private String photo;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "link")
    private String link;
    @ColumnInfo(name = "going_count")
    private int going_count;
    @ColumnInfo(name = "went_count")
    private int went_count;
    @ColumnInfo(name = "description_raw")
    private String description_raw;
    @ColumnInfo(name = "artist")
    private String[] artist;

}
