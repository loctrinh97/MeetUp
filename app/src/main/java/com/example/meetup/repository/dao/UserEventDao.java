package com.example.meetup.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.News;
import com.example.meetup.model.dataLocal.UsersEvents;

import java.util.List;

@Dao
public interface UserEventDao {
    @Query("select * from users_events ue , events e where ue.id == e.id and ue.status == 2")
    List<UsersEvents> getListEventsJoined();

    @Query("select * from users_events ue , events e where ue.id == e.id and ue.status == 1")
    List<UsersEvents> getListEventsCanJoin();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvents(List<Event> events);
}
