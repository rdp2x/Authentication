package com.rdp.jwtbackend.service;

import com.rdp.jwtbackend.model.UserInfoModel;
import com.rdp.jwtbackend.model.UsersModel;
import com.rdp.jwtbackend.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {
    private UserRepo repo;

    public UserInfoService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersModel user = repo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserInfoModel(user);
    }

}

// This class is created bcz Spring Security doesn’t know your database. During login, it automatically calls "loadUserByUsername(String username)" and that's how it locates the correct user.

// Note: This type of class is usually named as ModelName + "Detailes" + Service. But as I was a beginner, I thought of naming classes as per my convenience is cool.
