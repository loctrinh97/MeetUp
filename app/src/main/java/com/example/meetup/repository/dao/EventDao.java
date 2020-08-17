package com.example.meetup.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.meetup.model.dataLocal.Event;

import java.util.List;

@Dao
public interface EventDao {
    @Query("select * from events  order by going_count desc limit :pageSize")
    List<Event> getListEvents(int pageSize);

    @Query("select count(id) from events")
    int getCountEvent();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvents(List<Event> events);

    @Query("delete from events")
    void deleteEvents();

    @Query("select e.id, e.my_status,e.went_count,e.venue_id, e.photo, e.name,e.schedule_start_date, e.going_count, v.name, v.contact_address, v.geo_long, v.geo_lat from events e, venues v where e.id =:eventNearId and e.venue_id == v.id ")
    List<Event> getEventById(int eventNearId);
}
