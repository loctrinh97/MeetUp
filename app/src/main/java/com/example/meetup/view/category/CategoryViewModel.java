package com.example.meetup.view.category;

import androidx.lifecycle.ViewModel;

import com.example.meetup.model.dataLocal.Category;
import com.example.meetup.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends ViewModel {
    CategoryRepository repository = CategoryRepository.getInstance();
    List<Category> list;

    public CategoryViewModel() {
        list = new ArrayList<>();
        list = repository.getCategories();
    }

    public List<Category> getList() {
        return list;
    }
}
