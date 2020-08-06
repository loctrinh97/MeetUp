package com.example.meetup.repository;

import com.example.meetup.repository.dao.NewsDAO;
import com.example.meetup.model.dataLocal.News;

import java.util.List;

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

    public List<News> getListNews(int pageSize){
        return dao.getListNews(pageSize);
    };

    public void insertNews(List<News> news){
       dao.insertNews(news);
    }
    public int getCountColumn(){
        return dao.getCountColum();
    }
    public void clearList(){
        dao.deleteNews();
    }

    public News getNews(int id){
       return dao.getNews(id);
    }


}
