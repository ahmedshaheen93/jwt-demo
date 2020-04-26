package com.shaheen.jwtdemo.service;

import com.shaheen.jwtdemo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  extends  UserDetailsService {
    List<User> findAll();
    User save(User user);
}
