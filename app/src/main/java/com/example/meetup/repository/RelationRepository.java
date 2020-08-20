package com.example.meetup.repository;

import com.example.meetup.model.dataLocal.EventsCategories;
import com.example.meetup.repository.dao.EventsCategoriesDao;

import java.util.List;

public class RelationRepository {
    private static RelationRepository repository;
    private EventsCategoriesDao dao;
    public static RelationRepository getInstance(){
        if(repository == null){
            repository = new RelationRepository(AppDatabase.getInstance().getEventsCategoriesDao());
        }
        return repository;
    }
    private RelationRepository(EventsCategoriesDao dao){
        this.dao = dao;
    }

    public void insert(EventsCategories eventsCategories){
        dao.insert(eventsCategories);
    }
    public List<Integer> getIdByCategory(int categoryId){
        return dao.listIdByCategory(categoryId);
    }
}
