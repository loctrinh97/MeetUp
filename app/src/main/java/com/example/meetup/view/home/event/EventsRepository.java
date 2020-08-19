package com.example.meetup.view.home.event;

import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.model.response.EventVenue;
import com.example.meetup.repository.AppDatabase;
import com.example.meetup.repository.dao.EventDao;
import com.example.meetup.model.dataLocal.Event;

import java.util.List;

public class EventsRepository {
    private static EventsRepository eventsRepository;
    public static EventsRepository getInstance(){
        if(eventsRepository==null){
            eventsRepository = new EventsRepository(AppDatabase.getInstance().getEventDao());
        }
        return eventsRepository;
    }
    private EventDao dao;
    public EventsRepository(EventDao dao){
        this.dao = dao;
    }

    public List<Event> getListEvent(int pageSize){
        return dao.getListEvents(pageSize);
    }
    public int getCountEvent(){
        return dao.getCountEvent();
    }
    public void insertEvent(List<Event> eventList){
        dao.insertEvents(eventList);
    }
    public void deleteEvents(){
        dao.deleteEvents();
    }

    public List<Event> getEventNear() {
       return dao.getEventNear();
    }
    public List<Venue> getVenuesNear() {
        return dao.getVenuesNear();
    }

    public Event getEvent(int eventId){
        return dao.getEvent(eventId);
    }
    public void updateEvent(int myStatus, int eventId){
        dao.updateEvent(myStatus,eventId);
    }

    public void updateEventNear(List<EventVenue> eventVenues) {
        for (int i = 0; i < eventVenues.size();i++){
            dao.updateVenues(eventVenues.get(i).getVenueId(),eventVenues.get(i).getContactAddress(),
                    eventVenues.get(i).getGeoLong(),eventVenues.get(i).getGeoLat());
            dao.updateDistance(eventVenues.get(i).getId(),eventVenues.get(i).getDistance());
        }
    }


}
