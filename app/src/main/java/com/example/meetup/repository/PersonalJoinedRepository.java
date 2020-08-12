package com.example.meetup.repository;

import android.widget.Toast;

import com.example.meetup.repository.dao.UserEventDao;

import java.util.ArrayList;

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

    public void updateUsersEvents(ArrayList listIdJoined, long status) {
//        userEventDao.updateUsersEvent(listIdJoined,status);
        for (int i =0 ; i < listIdJoined.size(); i++){
            int listId = (int) listIdJoined.get(i);
            userEventDao.updateUsersEvent(listId,status);
        }

    }

    public void getEventStatus(ArrayList listIdJoined) {
        for (int i =0 ; i < listIdJoined.size(); i++) {
            int idStatus = (int) listIdJoined.get(i);
           userEventDao.getUserEventStatus(idStatus);
        }
    }
}
