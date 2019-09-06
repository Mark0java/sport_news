package com.turtles.sport_news.controller;

import java.util.ArrayList;
import java.util.List;
import com.turtles.sport_news.convertor.CategoryConvertor;
import com.turtles.sport_news.dto.CategoryDTO;
import com.turtles.sport_news.entity.Category;
import com.turtles.sport_news.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryConvertor categoryConvertor;

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> createNewCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = categoryConvertor.fromCategoryDTO(categoryDTO);
        category = categoryService.createCategory(category);
        return new ResponseEntity<CategoryDTO> (categoryConvertor.toCategoryDTO(category), HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable(name = "id") Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<CategoryDTO>(categoryConvertor.toCategoryDTO(category), HttpStatus.OK);
    }

    @GetMapping("/category")
    public List<CategoryDTO> findAll(){
        ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category: categoryService.getAllCategories()){
            categoryDTOS.add(categoryConvertor.toCategoryDTO(category));
        }
        return categoryDTOS;
    }

    @DeleteMapping("/category/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        categoryService.delete(id);
    }
}
