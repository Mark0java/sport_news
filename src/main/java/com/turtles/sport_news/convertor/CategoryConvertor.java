package com.turtles.sport_news.convertor;

import com.turtles.sport_news.dto.CategoryRequest;
import com.turtles.sport_news.dto.CategoryResponse;
import com.turtles.sport_news.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryConvertor {
    public CategoryRequest toCategoryRequest(Category category){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName(category.getName());
        return categoryRequest;
    }


    public Category fromCategoryRequest(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.getName());
        return category;
    }

    public CategoryResponse toCategoryResponse(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }

    public Category fromCategoryResponce(CategoryResponse categoryResponse){
        Category category = new Category();
        category.setId(categoryResponse.getId());
        category.setName(categoryResponse.getName());
        return category;
    }
}
