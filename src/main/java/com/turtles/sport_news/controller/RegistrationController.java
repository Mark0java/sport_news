package com.turtles.sport_news.controller;

import com.turtles.sport_news.convertor.UserConvertor;
import com.turtles.sport_news.dto.SignUpDTO;
import com.turtles.sport_news.entity.User;
import com.turtles.sport_news.repository.UserRepository;
import com.turtles.sport_news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserConvertor userConvertor;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping(value = "/registration", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String createNewAccount(SignUpDTO signUpDTO) {

        userService.createUser(userConvertor.fromSignUpDTO(signUpDTO));
    return "redirect:/login";
    }
}

