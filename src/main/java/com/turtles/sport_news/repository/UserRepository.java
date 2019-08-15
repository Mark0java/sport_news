package com.turtles.sport_news.repository;

import com.turtles.sport_news.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User getOneByEmail(String email);
}
