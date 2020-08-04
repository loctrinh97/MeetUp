package com.example.meetup.repository;

import com.example.meetup.dao.NewsDAO;
import com.example.meetup.model.News;
import com.example.meetup.ulti.MyApplication;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class ListNewsRepository{

    private static ListNewsRepository listNewsRepository;
    public static ListNewsRepository getInstance(){

        if(listNewsRepository==null){
            listNewsRepository = new ListNewsRepository(AppDatabase.getInstance().getNewsDao());
        }
        return listNewsRepository;
    }
    private NewsDAO dao;

    public ListNewsRepository(NewsDAO dao){ this.dao = dao;}

    public Flowable<List<News>> getListNews(int pageSize){
        return dao.getListNews(pageSize);
    };

    public void insertNews(List<News> news){
       dao.insertNews(news);
    }


}
