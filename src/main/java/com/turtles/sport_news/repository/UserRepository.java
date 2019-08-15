package com.turtles.sport_news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository  extends JpaRepository {
    User getOneByEmail(String email);
}
