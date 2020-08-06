package com.example.meetup.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.News;
import com.example.meetup.repository.ListNewsRepository;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {
    ListNewsRepository repository = ListNewsRepository.getInstance();

    List<News> newsList;

    private  MutableLiveData<List<News>> list = new MutableLiveData<>();

    public MutableLiveData<List<News>> getCurrentList(){
        list.setValue(newsList);
        return list;
    }
    public NewsViewModel(){
        newsList = new ArrayList<>();
    }

    public List<News> getNewsList(int pageSize) {
        newsList = repository.getListNews(pageSize);
        list.postValue(newsList);
        return newsList;
    }
}