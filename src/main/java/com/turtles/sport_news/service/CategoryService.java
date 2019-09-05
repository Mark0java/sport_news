package com.turtles.sport_news.service;

import com.turtles.sport_news.dto.CategoryDTO;
import com.turtles.sport_news.entity.Category;
import com.turtles.sport_news.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        category = categoryRepository.save(category);
        return category;
    }

    public void delete(Long id) {
        categoryRepository.delete(findOne(id));
    }

    public Category findOne(Long id){
        return categoryRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Category with id " +id+" not exist"));
    }

    public List<Category> getAllCategories(){ return categoryRepository.findAll(); }
    public Category getCategoryByName(String name){
        return categoryRepository.getOneByName(name);
    }
    public Category getCategoryById(Long id){ return categoryRepository.getOneById(id); }
    public boolean isExist(String name){
        return categoryRepository.getOneByName(name) != null;
    }
}
