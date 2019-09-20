package com.turtles.sport_news.controller;

import com.turtles.sport_news.convertor.ViewConvertor;
import com.turtles.sport_news.dto.CategoryRequest;
import com.turtles.sport_news.dto.ViewRequest;
import com.turtles.sport_news.dto.ViewResponse;
import com.turtles.sport_news.entity.View;
import com.turtles.sport_news.repository.ViewRepository;
import com.turtles.sport_news.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ViewController {

    @Autowired
    private ViewService viewService;

    @Autowired
    private ViewConvertor viewConvertor;

    @Autowired
    private ViewRepository viewRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/view")
    public ResponseEntity<ViewRequest> createNewView(@RequestBody ViewRequest viewRequest){
            View view = viewConvertor.fromViewRequest(viewRequest);
            view = viewService.createView(view);
            return new ResponseEntity<ViewRequest>(viewConvertor.toViewRequest(view), HttpStatus.CREATED);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<ViewResponse> getView(@PathVariable(name = "id") Long id){
        View view = viewService.findOne(id);
        return new ResponseEntity<ViewResponse>(viewConvertor.toViewResponse(view), HttpStatus.OK);
    }

    @GetMapping("/category/{id}/views")
    public ArrayList<ViewResponse> getAllViews(@PathVariable(name = "id") Long id){
        ArrayList<ViewResponse> viewResponses = new ArrayList<>();

        for(View view: viewService.getAllViews(id)){
            viewResponses.add(viewConvertor.toViewResponse(view));
        }

        return viewResponses;
    }
}
