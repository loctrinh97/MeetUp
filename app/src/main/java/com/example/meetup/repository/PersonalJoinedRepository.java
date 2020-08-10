package com.example.meetup.repository;

import com.example.meetup.repository.dao.NewsDAO;

public class PersonalJoinedRepository {
    private static PersonalJoinedRepository personalJoinedRepository;

    public static PersonalJoinedRepository getInstance(){

        if(personalJoinedRepository==null){
            personalJoinedRepository = new PersonalJoinedRepository(AppDatabase.getInstance().getNewsDao());
        }
        return personalJoinedRepository;
    }
    private NewsDAO newsDAO;
    public PersonalJoinedRepository(NewsDAO newsDao) {
        this.newsDAO = newsDao;
    }

}
