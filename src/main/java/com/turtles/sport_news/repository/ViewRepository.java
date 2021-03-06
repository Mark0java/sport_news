package com.turtles.sport_news.repository;

import com.turtles.sport_news.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    ArrayList<View> getAllByCategoryId(Long id);
}
