package com.example.meetup.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.model.response.EventVenue;

import java.util.List;
@Dao
public interface EventDao {
    @Query("select * from events  order by going_count desc limit :pageSize")
    List<Event> getListEvents(int pageSize);
    @Query("select * from events where id = :eventId")
    Event getEvent(int eventId);
    @Query("select count(id) from events")
    int getCountEvent();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvents(List<Event> events);
    @Query("update events set my_status = :myStatus where id = :eventId")
    void updateEvent(int myStatus,int eventId);
    @Query("update events set category_id = :category_id where id = :eventId")
    void updateEventCategory(int category_id,int eventId);
    @Query("delete from events")
    void deleteEvents();
    @Query("select * from events e, venues v where e.venue_id == v.id and e.distance != 0.0")
    List<Event> getEventNear();
    @Query("update venues set geo_long = :geoLong, geo_lat = :geoLat, contact_address =:contactAddress where id =:venueId")
    void updateVenues(int venueId, String contactAddress, String geoLong, String geoLat);
    @Query("update events set distance =:distance where id =:id")
    void updateDistance(int id, double distance);
    @Query("select * from events e, venues v where v.id == e.venue_id and  e.distance != 0.0 ")
    List<Venue> getVenuesNear();

    @Query("select * from events where category_id = :categoryId Order by going_count desc limit :pageSize")
    List<Event> getListEventByCategory(int pageSize,int categoryId);
    @Query("select count(*) from events where category_id = :categoryId")
    int getCountEventByCategory(int categoryId);
}
