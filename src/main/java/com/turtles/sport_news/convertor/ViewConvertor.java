package com.turtles.sport_news.convertor;

import com.turtles.sport_news.dto.ViewRequest;
import com.turtles.sport_news.dto.ViewResponse;
import com.turtles.sport_news.entity.View;
import com.turtles.sport_news.repository.ViewRepository;
import com.turtles.sport_news.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewConvertor {

    @Autowired
    private ViewRepository viewRepository;

    @Autowired
    private CategoryService categoryService;

    public ViewRequest toViewRequest(View view){
        ViewRequest viewRequest = new ViewRequest();

        viewRequest.setText(view.getText());
        viewRequest.setTitle(view.getTitle());
        viewRequest.setImgURL(view.getImgURL());
        viewRequest.setCategoryId(view.getCategory().getId());

        return viewRequest;
    }

    public View fromViewRequest(ViewRequest viewRequest){
        View view = new View();

        view.setImgURL(viewRequest.getImgURL());
        view.setText(viewRequest.getText());
        view.setTitle(viewRequest.getTitle());
        view.setCategory(categoryService.getCategoryById(viewRequest.getCategoryId()));

        return view;
    }

    public ViewResponse toViewResponse(View view){
        ViewResponse viewResponse = new ViewResponse();

        viewResponse.setId(view.getId());
        viewResponse.setText(view.getText());
        viewResponse.setTitle(view.getTitle());
        viewResponse.setImgURL(view.getImgURL());
        viewResponse.setCategoryId(view.getCategory().getId());

        return viewResponse;
    }

    public View fromViewResponse(ViewResponse viewResponse) {
        View view = new View();

        view.setId(viewResponse.getId());
        view.setImgURL(viewResponse.getImgURL());
        view.setText(viewResponse.getText());
        view.setTitle(viewResponse.getTitle());
        view.setCategory(categoryService.getCategoryById(viewResponse.getCategoryId()));

        return view;

    }
}
