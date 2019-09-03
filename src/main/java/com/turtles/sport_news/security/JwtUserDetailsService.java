package com.turtles.sport_news.security;

import com.turtles.sport_news.entity.User;
import com.turtles.sport_news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private UserDetails createNewUser(String login, String password){
        return new JwtUser(login, password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getOneByEmail(username);
        if (user != null){
            return createNewUser(user.getEmail(), user.getPassword());
        }
        return null;
    }

    public User registerUser(User user) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setPassword(bcrypt.encode(user.getPassword()));
        user = userRepository.save(user);
        return user;
    }
}
