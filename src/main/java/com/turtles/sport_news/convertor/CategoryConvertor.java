package com.turtles.sport_news.convertor;

import com.turtles.sport_news.dto.CategoryDTO;
import com.turtles.sport_news.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryConvertor {
    public CategoryDTO toCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }


    public Category fromCategoryDTO(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
}
