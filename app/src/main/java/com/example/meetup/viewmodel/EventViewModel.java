package com.example.meetup.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.News;
import com.example.meetup.repository.EventsRepository;
import com.example.meetup.repository.ListNewsRepository;

import java.util.ArrayList;
import java.util.List;

public class EventViewModel extends ViewModel{
   EventsRepository eventsRepository = EventsRepository.getInstance();
   List<Event> eventList;

    private MutableLiveData<List<Event>> list = new MutableLiveData<>();

    public MutableLiveData<List<Event>> getList() {
        return list;
    }

    public EventViewModel(){
        eventList = new ArrayList<>();
    }

    public List<Event> getEventList(int pageSize) {
        eventList = eventsRepository.getListEvent(pageSize);
        list.setValue(eventList);
        return eventList;
    }
}
