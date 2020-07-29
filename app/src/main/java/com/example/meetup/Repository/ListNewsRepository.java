package com.example.meetup.Repository;

import android.app.Application;

import com.example.meetup.DAO.NewsDAO;
import com.example.meetup.Database.AppDatabase;
import com.example.meetup.Model.News;
import com.example.meetup.MyApplication;

import java.util.List;

import io.reactivex.Flowable;

public class ListNewsRepository{

    private static ListNewsRepository listNewsRepository;
    public static ListNewsRepository getInstance(){

        if(listNewsRepository==null){
            listNewsRepository = new ListNewsRepository(AppDatabase.getInstance(MyApplication.getAppContext()).getNewsDao());
        }
        return listNewsRepository;
    }
    private NewsDAO dao;

    public ListNewsRepository(NewsDAO dao){ this.dao = dao;}

    public Flowable<List<News>> getListNews(int pageSize){
        return dao.getListNews(pageSize);
    };

    public void insertNews(News... news){
       dao.insertNews(news);
    }

    public void updateNews(News... news){
        dao.updateNews(news);
    }
}
