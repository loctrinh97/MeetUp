package com.example.meetup.repository;

import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.repository.dao.CategoryDao;

import java.util.List;

public class CategoryRepository {
    private static CategoryRepository categoryRepository = null;
    public static CategoryRepository getInstance(){
        if(categoryRepository == null){
            categoryRepository= new CategoryRepository(AppDatabase.getInstance().getCategoryDao());
        }
        return categoryRepository;
    }
    private CategoryDao dao;
    public CategoryRepository(CategoryDao dao){
        this.dao = dao;
    }

    public List<Category> getCategories(){
        return dao.getListCategories();
    }
    public void insertCategories(List<Category> categories){
        dao.insertCategories(categories);
    }
    public void deleteCategories(){
        dao.deleteCategories();
    }
    public int getCountCategories(){
        return dao.getCountCategories();
    }

}
