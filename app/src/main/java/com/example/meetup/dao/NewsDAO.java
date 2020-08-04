package com.example.meetup.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.meetup.model.News;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
@Dao
public interface NewsDAO {
    @Query("SELECT * from news order by publish_date desc limit :pageSize")
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
