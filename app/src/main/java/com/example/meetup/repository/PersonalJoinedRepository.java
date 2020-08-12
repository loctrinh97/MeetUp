package com.example.meetup.repository;

import com.example.meetup.model.dataLocal.UsersEvents;
import com.example.meetup.repository.dao.NewsDAO;
import com.example.meetup.repository.dao.UserEventDao;

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

    public List<UsersEvents> getJoinedList() {
        return userEventDao.getListEventsJoined();
    }
}
