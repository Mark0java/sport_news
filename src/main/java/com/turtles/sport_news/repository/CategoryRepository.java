package com.turtles.sport_news.repository;

import com.turtles.sport_news.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
        Category getOneByName(String name);
        Category getOneById(Long id);

}
