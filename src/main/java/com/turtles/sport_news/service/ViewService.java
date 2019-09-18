package com.turtles.sport_news.service;

import com.turtles.sport_news.entity.View;
import com.turtles.sport_news.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ViewService {
    @Autowired
    private ViewRepository viewRepository;

    public void delete(Long id){
        viewRepository.delete(findOne(id));
    }

    public View createView(View view){
        return viewRepository.save(view);
    }

    public View findOne(Long id){
        return viewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("View with id " +id+" not exist"));
    }

    public ArrayList<View> getAllViews(Long id){
        return viewRepository.getAllByCategoryId(id);
    }
}
