package com.turtles.sport_news.convertor;

import com.turtles.sport_news.dto.SignUpDTO;
import com.turtles.sport_news.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserConvertor {
    public SignUpDTO toSignUpDTO(User user){
        SignUpDTO signUpDTO = new SignUpDTO();

        signUpDTO.setName(user.getName());
        signUpDTO.setSurname(user.getSurname());
        signUpDTO.setEmail(user.getEmail());
        signUpDTO.setPassword(user.getPassword());

        return signUpDTO;
    }

    public User fromSignUpDTO(SignUpDTO signUpDTO){
        User user = new User();

        user.setName(signUpDTO.getName());
        user.setSurname(signUpDTO.getSurname());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(signUpDTO.getPassword());

        return user;
    }
}
