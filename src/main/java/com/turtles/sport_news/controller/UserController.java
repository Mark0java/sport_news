package com.turtles.sport_news.controller;

import com.turtles.sport_news.convertor.UserConvertor;
import com.turtles.sport_news.dto.SignUpDTO;
import com.turtles.sport_news.entity.User;
import com.turtles.sport_news.service.UserService;
import com.turtles.sport_news.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private UserConvertor userConvertor;

    @PostMapping("/account")
    public ResponseEntity<SignUpDTO> createNewAccount(@RequestBody SignUpDTO signUpDTO) {
        if (!validate(signUpDTO)) {
            return ResponseEntity.badRequest().build();
        }
        User account = userService.createUser(userConvertor.fromSignUpDTO(signUpDTO));
        return new ResponseEntity<SignUpDTO>(userConvertor.toSignUpDTO(account), HttpStatus.CREATED);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<SignUpDTO> getAccount(@PathVariable(name = "id") Long id) {
        User account = userService.getUserById(id);
        return new ResponseEntity<SignUpDTO>(userConvertor.toSignUpDTO(account), HttpStatus.OK);
    }


    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);
    }

    private boolean validate(SignUpDTO signUpDTO) {
        if (validationService.allExist(signUpDTO.getEmail(), signUpDTO.getPassword(), signUpDTO.getConfirmPassword(), signUpDTO.getEmail(), signUpDTO.getName(), signUpDTO.getSurname())) {
            return signUpDTO.getConfirmPassword().equals(signUpDTO.getPassword());
        }
        return false;
    }


}
