package com.rdp.authbackend.controller;

import com.rdp.authbackend.model.UsersModel;
import com.rdp.authbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/register")
    public UsersModel register(@RequestBody UsersModel user) {
        return service.register(user);
    }

}

// This class basically is used for registering a new user. That's it!!