package com.alireza.service;

import com.alireza.model.Categories;
import com.alireza.repository.CategoryRepository;

import java.util.List;

public class CategoryService {
    public boolean createCategory(Categories categories){
        if (CategoryRepository.findCategory(String.valueOf(categories.getCategory())) == null){
            CategoryRepository.createCategory(categories);
            return true;
        }
        else {
            return false;
        }
    }

    public void updateCategory(Categories categories){
        CategoryRepository.updateCategory(categories);
    }

    public List<Categories> findAll(){
        return CategoryRepository.findAllCategory();
    }
}
