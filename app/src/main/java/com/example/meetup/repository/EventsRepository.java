package com.example.meetup.repository;

import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.model.response.EventVenue;
import com.example.meetup.repository.AppDatabase;
import com.example.meetup.repository.dao.EventDao;
import com.example.meetup.model.dataLocal.Event;

import java.util.List;

public class EventsRepository {
    private static EventsRepository eventsRepository;

    public static EventsRepository getInstance() {
        if (eventsRepository == null) {
            eventsRepository = new EventsRepository(AppDatabase.getInstance().getEventDao());
        }
        return eventsRepository;
    }

    private EventDao dao;

    public EventsRepository(EventDao dao) {
        this.dao = dao;
    }

    public List<Event> getListEvent(int pageSize) {
        return dao.getListEvents(pageSize);
    }

    public int getCountEvent() {
        return dao.getCountEvent();
    }

    public void insertEvent(List<Event> eventList) {
        dao.insertEvents(eventList);
    }

    public void deleteEvents() {
        dao.deleteEvents();
    }

    public List<Event> getEventNear() {
        return dao.getEventNear();
    }

    public List<Venue> getVenuesNear() {
        return dao.getVenuesNear();
    }

    public Event getEvent(int eventId) {
        return dao.getEvent(eventId);
    }

    public void updateEvent(int myStatus, int eventId) {
        dao.updateEvent(myStatus, eventId);
    }

    public void updateEventNear(List<EventVenue> eventVenues) {
        for (int i = 0; i < eventVenues.size(); i++) {
            dao.updateVenues(eventVenues.get(i).getVenueId(), eventVenues.get(i).getContactAddress(),
                    eventVenues.get(i).getGeoLong(), eventVenues.get(i).getGeoLat());
            dao.updateDistance(eventVenues.get(i).getId(), eventVenues.get(i).getDistance());
        }
    }


    public void updateEventCategory(int category_id, int eventID) {
        dao.updateEventCategory(category_id, eventID);
    }

    public List<Event> getEventByCategory(int pageSize, int categoryId) {
        return dao.getListEventByCategory(pageSize, categoryId);
    }

    public int getCountEventByCategory(int categoryId) {
        return dao.getCountEventByCategory(categoryId);
    }

    public List<Event> getListWithTime(int pageSize, int categoryId) {
        return dao.getListWithTime(pageSize, categoryId);
    }

    public List<Event> getListEnd(int pageSize,int categoryId,String date){
        return dao.getListEnd(pageSize, categoryId, date);
    }
    public List<Event> getListHappen(int pageSize,int categoryId,String date){
        return dao.getListHappen(pageSize, categoryId, date);
    }

    public int getCountEnd(int categoryId,String date){
        return dao.getCountEnd(categoryId, date);
    }
    public int getCountHappen(int categoryId,String date){
        return dao.getCountHappen(categoryId, date);
    }

}
