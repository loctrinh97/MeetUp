package com.example.meetup.repository.dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.meetup.model.dataLocal.Venue;

public interface VenueDao {
    @Query("select count(id) from venues")
    int getCountVenues();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVenue(Venue venue);
}
