package com.example.meetup.view.home.event;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.repository.CategoryRepository;
import com.example.meetup.repository.EventsRepository;
import com.example.meetup.repository.VenueRepository;

import java.util.ArrayList;
import java.util.List;

public class EventViewModel extends ViewModel{
    CategoryRepository categoryRepository = CategoryRepository.getInstance();
    VenueRepository venueRepository = VenueRepository.getInstance();
    EventsRepository eventsRepository = EventsRepository.getInstance();
    List<Event> eventList;
   int page  = 0;

    private MutableLiveData<List<Event>> list = new MutableLiveData<>();

    public MutableLiveData<List<Event>> getList() {
        return list;
    }

    public EventViewModel(){
        eventList = new ArrayList<>();
    }

    public List<Event> getEventList(int pageSize) {
        page = pageSize;
        eventList = eventsRepository.getListEvent(pageSize);
        list.postValue(eventList);
        return eventList;
    }
    public void updateEvent(int myStatus, int eventId){
        eventsRepository.updateEvent(myStatus,eventId);
    }
    public int getCount(){
        return eventsRepository.getCountEvent();
    }
    public void refresh(){
        eventList = eventsRepository.getListEvent(page);
        list.setValue(eventList);
    }
    public List<Event> getEventNearList(){
        // Fake data
        return eventsRepository.getListEvent(5);
    }
    public Category getCategory(int categoryId){
        return categoryRepository.getCategory(categoryId);
    };
    public Venue getVenue(int venueId){
        return venueRepository.getVenue(venueId);
    }
}
