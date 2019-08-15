package com.turtles.sport_news.service;

import com.turtles.sport_news.entity.User;
import com.turtles.sport_news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user){
        if(isExist(user.getEmail())){
            return getUserByEmail(user.getEmail());
        }
        user = userRepository.save(user);
        return user;
    }

    public User getUserByEmail(String email){
        return userRepository.getOneByEmail(email);
    }

    public boolean isExist(String email){
        return userRepository.getOneByEmail(email) != null;
    }
}
