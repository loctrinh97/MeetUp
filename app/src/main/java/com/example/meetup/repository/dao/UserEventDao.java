package com.example.meetup.repository.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserEventDao {
//    @Query("select * from users_events ue , events e where ue.id == e.id and ue.status == 2")
//    List<UsersEvents> getListEventsJoined();
//
//    @Query("select * from users_events ue , events e where ue.id == e.id and ue.status == 1")
//    List<UsersEvents> getListEventsCanJoin();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertEvents(List<Event> events);

    @Query("update events set my_status =:status where id = :listId ")
    void updateUsersEvent(int listId, long status);
    @Query("delete from users_events")
    void deleteUsersEvents();
    @Query("select my_status from events where id =:idStatus")
    int getUserEventStatus(int idStatus);
}
