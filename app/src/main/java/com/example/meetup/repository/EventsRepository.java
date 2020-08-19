package com.example.meetup.repository;

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

    public void getEventById(List<Integer> listEventNearId) {
//        for (int i = 0; i < listEventNearId.size();i++){
//            dao.getEventById(listEventNearId.get(i));
//        }
    }
    public Event getEvent(int eventId){
        return dao.getEvent(eventId);
    }
    public void updateEvent(int myStatus, int eventId){
        dao.updateEvent(myStatus,eventId);
    }
    public void updateEventCategory(int category_id,int eventID){
        dao.updateEventCategory(category_id,eventID);
    }
    public List<Event> getEventByCategory(int pageSize,int categoryId){
        return dao.getListEventByCategory(pageSize,categoryId);
    }
    public  int getCountEventByCategory(int categoryId ){
        return dao.getCountEventByCategory(categoryId);
    }

}
