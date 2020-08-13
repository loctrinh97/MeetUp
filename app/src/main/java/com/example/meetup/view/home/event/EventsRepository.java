package com.example.meetup.view.home.event;

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
    public void updateEvent(int myStatus, int eventId){
        dao.updateEvent(myStatus,eventId);
    }
}
