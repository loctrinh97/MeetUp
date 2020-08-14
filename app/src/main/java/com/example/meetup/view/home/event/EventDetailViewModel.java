package com.example.meetup.view.home.event;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.repository.CategoryRepository;
import com.example.meetup.repository.VenueRepository;
import com.example.meetup.ulti.MyApplication;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EventDetailViewModel extends ViewModel {
    CategoryRepository categoryRepository = CategoryRepository.getInstance();
    VenueRepository venueRepository = VenueRepository.getInstance();
    EventsRepository eventsRepository  = EventsRepository.getInstance();
    List<Event> nearList;

    public EventDetailViewModel(){
        nearList = new ArrayList<>();
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
