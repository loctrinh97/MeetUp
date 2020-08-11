package com.example.meetup.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.meetup.model.dataLocal.Event;

import java.util.List;
@Dao
public interface EventDao {
    @Query("select * from events")
    List<Event> getListEvents();
    @Query("select count(id) from events")
    int getCountEvent();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvents(List<Event> events);
    @Query("delete from events")
    void deleteEvents();
}