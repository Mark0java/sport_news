package com.turtles.sport_news.convertor;

import com.turtles.sport_news.dto.ViewDTO;
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

    public ViewDTO toViewDTO(View view){
        ViewDTO viewDTO = new ViewDTO();

        viewDTO.setText(view.getText());
        viewDTO.setTitle(view.getTitle());
        viewDTO.setImgURL(view.getImgURL());
        viewDTO.setCategoryId(view.getCategory().getId());

        return viewDTO;
    }

    public View fromViewDTO(ViewDTO viewDTO) {
        View view = new View();

        view.setImgURL(viewDTO.getImgURL());
        view.setText(viewDTO.getText());
        view.setTitle(viewDTO.getTitle());
        view.setCategory(categoryService.getCategoryById(viewDTO.getCategoryId()));

        return view;

    }
}
