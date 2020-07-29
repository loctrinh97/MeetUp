package com.example.meetup.Database;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.meetup.DAO.NewsDAO;
import com.example.meetup.Model.News;

@Database(entities = {News.class},version = AppDatabase.DATABASE_VERSION )
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase database=null;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Meetup_database";



    public abstract NewsDAO getNewsDao();

    public static AppDatabase getInstance(Context context){
        if(database == null){
            database= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
