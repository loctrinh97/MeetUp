package com.example.meetup.view.category.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.repository.EventsRepository;
import com.example.meetup.ulti.Define;
import com.example.meetup.view.home.event.EventViewModel;

import java.util.Date;
import java.util.List;

public class SearchViewModel extends EventViewModel {

    EventsRepository eventsRepository = EventsRepository.getInstance();

    private MutableLiveData<List<Event>> listEnd = new MutableLiveData<>();
    private MutableLiveData<List<Event>> listHappen = new MutableLiveData<>();

    public MutableLiveData<List<Event>> getListEnd() {
        return listEnd;
    }
    public MutableLiveData<List<Event>> getListHappen() {
        return listHappen;
    }

    public  List<Event> getListEnd(int pageSize,String keyword){
        Date date = Define.currentTime;
        String dateString = Define.simpleDateFormat.format(date) ;
        List<Event> list = eventsRepository.getListEnd(pageSize,keyword, dateString);
        listEnd.postValue(list);
        return list;
    }
    public List<Event> getListHappen(int pageSize, String keyword){
        Date date = Define.currentTime;
        String dateString =Define.simpleDateFormat.format(date);
        List<Event> list = eventsRepository.getListHappen(pageSize,keyword, dateString);
        listHappen.postValue(list);
        return list;
    }

    public int getCountHappen(String keyword){
        Date date = Define.currentTime;
        String dateString =Define.simpleDateFormat.format(date);
        return eventsRepository.getCountHappen(keyword, dateString);
    }
    public int getCountEnd(String keyword){
        Date date = Define.currentTime;
        String dateString =Define.simpleDateFormat.format(date) ;
        return eventsRepository.getCountEnd(keyword, dateString);
    }
}