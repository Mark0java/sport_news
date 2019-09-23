package com.turtles.sport_news.controller;

import java.util.ArrayList;
import java.util.List;
import com.turtles.sport_news.convertor.CategoryConvertor;
import com.turtles.sport_news.dto.CategoryRequest;
import com.turtles.sport_news.dto.CategoryResponse;
import com.turtles.sport_news.entity.Category;
import com.turtles.sport_news.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryConvertor categoryConvertor;

    @PostMapping("/category")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryRequest> createNewCategory(@RequestBody CategoryRequest categoryRequest){
        Category category = categoryConvertor.fromCategoryRequest(categoryRequest);
        category = categoryService.createCategory(category);
        return new ResponseEntity<> (categoryConvertor.toCategoryRequest(category), HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable(name = "id") Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryConvertor.toCategoryResponse(category), HttpStatus.OK);
    }

    @GetMapping("/category")
    public List<CategoryResponse> findAll(){
        ArrayList<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category: categoryService.getAllCategories()){
            categoryResponses.add(categoryConvertor.toCategoryResponse(category));
        }
        return categoryResponses;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/category/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        categoryService.delete(id);
    }
}
