package com.example.meetup.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.meetup.model.dataLocal.Category;


import java.util.List;
@Dao
public interface CategoryDao {
    @Query("select * from categories")
    List<Category> getListCategories();
    @Query("select * from categories where id = :id")
    Category getCategory(int id);
    @Query("select count(id) from categories")
    int getCountCategories();
    @Query("delete from categories")
    void deleteCategories();

    @Insert
    void insertCategories(List<Category> categories );
}
