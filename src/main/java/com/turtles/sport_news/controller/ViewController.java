package com.turtles.sport_news.controller;

import com.turtles.sport_news.convertor.ViewConvertor;
import com.turtles.sport_news.dto.ViewDTO;
import com.turtles.sport_news.entity.View;
import com.turtles.sport_news.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ViewController {

    @Autowired
    private ViewService viewService;

    @Autowired
    private ViewConvertor viewConvertor;

    @PostMapping("/view")
    public ResponseEntity<ViewDTO> createNewView(@RequestBody ViewDTO viewDTO){
            View view = viewConvertor.fromViewDTO(viewDTO);
            view = viewService.createView(view);
            return new ResponseEntity<ViewDTO>(viewConvertor.toViewDTO(view), HttpStatus.CREATED);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<ViewDTO> findOne(@PathVariable(name = "id") Long id){
        View view = viewService.findOne(id);
        return new ResponseEntity<ViewDTO>(viewConvertor.toViewDTO(view), HttpStatus.CREATED);
    }
}
