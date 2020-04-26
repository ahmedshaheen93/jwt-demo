package com.shaheen.jwtdemo.utils;

import com.shaheen.jwtdemo.model.User;
import com.shaheen.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstTimeInit implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Override
    public void run(String... args) throws Exception {
       if( userService.findAll().isEmpty()){
           User admin = new User("ahmed", "shaheen", "01061510304", "ahmedshaheen676@yahoo.com", "adminPassword");
           userService.save(admin);
       }
    }
}
