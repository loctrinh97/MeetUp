package com.example.meetup.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.meetup.Model.News;

import java.util.List;

import io.reactivex.Flowable;
@Dao
public interface NewsDAO {
    @Query("SELECT * from news order by publish_date desc limit :pageSize")
    Flowable<List<News>> getListNews(int pageSize);

    @Insert
    void insertNews(News... news);

    @Update
    void updateNews(News... news);
}
