package com.example.meetup.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.meetup.model.dataLocal.News;

import java.util.List;

@Dao
public interface NewsDAO {
    @Query("SELECT * from news limit :pageSize")
    List<News> getListNews(int pageSize);

    @Query("SELECT * from news where new_id = :id")
    News getNews(int id);
    @Query("Select count(new_id) from news")
    int getCountColum();
    @Insert
    void insertNews(List<News> news);


    @Query("Delete from news")
    void deleteNews();
}
