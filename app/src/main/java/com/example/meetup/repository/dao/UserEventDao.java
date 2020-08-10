package com.example.meetup.repository.dao;

import androidx.room.Query;

import com.example.meetup.model.dataLocal.News;
import com.example.meetup.model.dataLocal.UsersEvents;

import java.util.List;

public interface UserEventDao {
    @Query("select * from users_events ue , events e where ue.id == e.id and ue.status == 2")
    List<UsersEvents> getListEventsJoined(int pageSize);

    @Query("select * from users_events ue , events e where ue.id == e.id and ue.status == 1")
    List<UsersEvents> getListEventsCanJoin(int pageSize);
}
