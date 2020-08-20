package com.example.meetup.view.category.time;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.view.home.event.EventViewModel;

import java.util.List;

public class SearchViewModel extends EventViewModel {


    protected MutableLiveData<List<Event>> list = new MutableLiveData<>();

    public MutableLiveData<List<Event>> getList() {
        return list;
    }


}