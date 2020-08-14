package com.example.meetup.view.personal.joined;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.UsersEvents;
import com.example.meetup.repository.PersonalJoinedRepository;

import java.util.ArrayList;
import java.util.List;

public class JoinedViewModel extends ViewModel {
    PersonalJoinedRepository personalJoinedRepository = PersonalJoinedRepository.getInstance();
    List<Event> events;

    private MutableLiveData<List<Event>> personalJoinedList = new MutableLiveData<>();
    private MutableLiveData<List<Event>> personalCanJoinList = new MutableLiveData<>();


    public MutableLiveData<List<Event>> getList() {
        return personalJoinedList;
    }
    public MutableLiveData<List<Event>> getCanJoinList() {
        return personalCanJoinList;
    }

    public MutableLiveData<List<Event>> getCurrentList(){
        personalJoinedList.setValue(events);
        return personalJoinedList;
    }

    public JoinedViewModel(){
        events = new ArrayList<>();
    }

    public List<Event> getUserEventList(int pageSize, int status) {
        events = personalJoinedRepository.getListEventJoined(pageSize,status);
        personalJoinedList.postValue(events);
        return events;
    }

    public List<Event> getUserCanJoin(int pageSize, int status) {
        events = personalJoinedRepository.getListEventJoined(pageSize,status);
        personalCanJoinList.postValue(events);
        return events;
    }}
