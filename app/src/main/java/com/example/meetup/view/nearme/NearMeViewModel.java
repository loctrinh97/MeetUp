package com.example.meetup.view.nearme;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.view.home.event.EventsRepository;

import java.util.List;

public class NearMeViewModel extends ViewModel {
    EventsRepository eventsRepository = EventsRepository.getInstance();
    List<Event> events;
    private MutableLiveData<List<Event>> nearMeList = new MutableLiveData<>();

    public List<Event> getEventNearMe() {
        return null;
    }
}
