package com.example.meetup.repository;

import android.widget.Toast;

import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.model.dataLocal.Event;
import com.example.meetup.repository.dao.UserEventDao;

import java.util.ArrayList;
import java.util.List;

public class PersonalJoinedRepository {
    private static PersonalJoinedRepository personalJoinedRepository;

    public static PersonalJoinedRepository getInstance(){

        if(personalJoinedRepository==null){
            personalJoinedRepository = new PersonalJoinedRepository(AppDatabase.getInstance().getUserEventDao());
        }
        return personalJoinedRepository;
    }
    private UserEventDao userEventDao;
    public PersonalJoinedRepository(UserEventDao userEventDao) {
        this.userEventDao = userEventDao;
    }

    public void deleteUsersEvents() {
        userEventDao.deleteUsersEvents();
    }

    public void updateUsersEvents(List listIdJoined, long status) {
        for (int i =0 ; i < listIdJoined.size(); i++){
            int listId = (int) listIdJoined.get(i);
            userEventDao.updateUsersEvent(listId,status);
        }
    }

    public List<Event> getListEventJoined(int pageSize,long status){
        return userEventDao.getListEventJoined(pageSize,status);
    }

}
