package com.example.meetup.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meetup.model.News;
import com.example.meetup.repository.ListNewsRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {

    ListNewsRepository repository = ListNewsRepository.getInstance();

    List<News> newsList;


    private  MutableLiveData<List<News>> list;

    public MutableLiveData<List<News>> getCurrentList(){
        if(list == null){
            list= new MutableLiveData<>();
            list.setValue(newsList);
        }
        return list;
    }


    public NewsViewModel(){
        newsList = new ArrayList<>();
    }

    public News getNews(int id){
        return repository.getNews(id);
    }
    public void fillList(View view){
        Log.d("Test", "Size"+repository.getCountColumn());
    }

    public void clearList(View view){
        newsList.clear();
        list.setValue(newsList);
    }

//    public List<News> getNewsList(int pageSize) {
//        repository.getListNews(pageSize)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({list ->  getNewsList(pageSize)},
//                        )
//        repository.getListNews(pageSize);
//    }


    public List<News> getNewsList(int pageSize) {
        return repository.getListNews(pageSize);
    }
}