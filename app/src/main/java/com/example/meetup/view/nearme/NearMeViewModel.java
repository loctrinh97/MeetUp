package com.example.meetup.view.nearme;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.model.response.EventVenue;
import com.example.meetup.repository.EventsRepository;

import java.util.List;

public class NearMeViewModel extends ViewModel {
    EventsRepository eventsRepository = EventsRepository.getInstance();
    List<Event> events;
    List<Venue> venues;
    private MutableLiveData<List<Event>> eventNearMeList = new MutableLiveData<>();
    private MutableLiveData<List<Venue>> venuesNearMeList = new MutableLiveData<>();

    public List<Event> getEventNearMe() {
        events = eventsRepository.getEventNear();
        eventNearMeList.postValue(events);
        return events;
    }

    public List<Venue> getVenuesNearMe() {
        venues = eventsRepository.getVenuesNear();
        venuesNearMeList.postValue(venues);
        return venues;
    }
}
