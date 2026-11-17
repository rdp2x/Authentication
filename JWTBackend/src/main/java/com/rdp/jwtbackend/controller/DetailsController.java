package com.rdp.jwtbackend.controller;

import com.rdp.jwtbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailsController {

    @Autowired
    UserService service;

    @RequestMapping("/")
    public String hello() {
        return "The current users that is logged in is: " + service.getCurrentUser();
    }

    @GetMapping("/current-user")
    public String getCurrentUsername() {
        return service.getCurrentUser();
    }

}
