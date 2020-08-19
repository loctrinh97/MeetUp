package com.example.meetup.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.meetup.model.dataLocal.Venue;

import java.util.List;

@Dao
public interface VenueDao {
    @Query("Select * from venues where id=:venueId")
    Venue getVenue(int venueId);
    @Query("select count(id) from venues")
    int getCountVenues();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVenue(Venue venue);
}
