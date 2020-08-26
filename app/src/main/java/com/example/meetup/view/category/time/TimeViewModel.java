package com.example.meetup.view.category.time;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.repository.EventsRepository;

import java.util.List;

public class TimeViewModel extends ViewModel {
    EventsRepository repository = EventsRepository.getInstance();

    private MutableLiveData<List<Event>> list = new MutableLiveData<>();

    public MutableLiveData<List<Event>> getList() {
        return list;
    }
    public List<Event> getEventList(int pageSize,int categoryId){
        List<Event> eventList = repository.getListWithTime(pageSize,categoryId);
        list.postValue(eventList);
        return eventList;
    }
}
