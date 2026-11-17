package com.rdp.authbackend.service;

import com.rdp.authbackend.model.UsersModel;
import com.rdp.authbackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo repo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public UsersModel register(UsersModel user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}

