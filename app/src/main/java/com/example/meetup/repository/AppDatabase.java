package com.example.meetup.repository;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.meetup.repository.dao.CategoryDao;
import com.example.meetup.repository.dao.EventDao;
import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.model.dataLocal.EventsCategories;
import com.example.meetup.model.dataLocal.UsersEvents;
import com.example.meetup.model.dataLocal.Venue;
import com.example.meetup.repository.dao.UserEventDao;
import com.example.meetup.repository.dao.VenueDao;
import com.example.meetup.ulti.MyApplication;
import com.example.meetup.repository.dao.NewsDAO;
import com.example.meetup.model.dataLocal.News;

@Database(entities = {News.class, Event.class, Category.class, Venue.class, EventsCategories.class, UsersEvents.class},version = AppDatabase.DATABASE_VERSION,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase database=null;
    public static final int DATABASE_VERSION = 9;
    public static final String DATABASE_NAME = "Meetup_database";

    public abstract VenueDao getVenueDao();
    public abstract CategoryDao getCategoryDao();
    public abstract NewsDAO getNewsDao();
    public abstract EventDao getEventDao();
    public abstract UserEventDao getUserEventDao();
    public static AppDatabase getInstance(){
        if(database == null){
            database= Room.databaseBuilder(MyApplication.getAppContext(),AppDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
