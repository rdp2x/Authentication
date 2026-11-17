package com.rdp.jwtbackend.controller;

import com.rdp.jwtbackend.model.UsersModel;
import com.rdp.jwtbackend.service.UserInfoService;
import com.rdp.jwtbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/register")
    public UsersModel register(@RequestBody UsersModel user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UsersModel user) {
        return service.verify(user);
    }

}

// This class basically is used for registering a new user. That's it!!