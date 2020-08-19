package com.example.meetup.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.meetup.model.dataLocal.EventsCategories;

import java.util.List;

@Dao
public interface EventsCategoriesDao {
    @Insert
    void insert(EventsCategories categories);
    @Query("select event_id from events_categories where category_id = :category")
    List<Integer> listIdByCategory(int category);
}
