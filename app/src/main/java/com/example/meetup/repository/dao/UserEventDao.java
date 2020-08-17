package com.example.meetup.repository.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.meetup.model.dataLocal.Event;
import java.util.List;

@Dao
public interface UserEventDao {

    @Query("update events set my_status =:status where id = :listId ")
    void updateUsersEvent(int listId, long status);
    @Query("delete from users_events")
    void deleteUsersEvents();
    @Query("select * from events where my_status=:status limit:pageSize")
    List<Event> getListEventJoined(int pageSize, long status);
}
