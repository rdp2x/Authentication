package com.rdp.jwtbackend.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserInfoModel implements UserDetails {

    private UsersModel user;

    public UserInfoModel(UsersModel user) {
        this.user = user;
    }

    // Roles for Authentication
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("User"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}

// This class implements UserDetails so Spring Security can use it during login.
// Spring doesn’t work directly with our UsersModel Class, so this class gives it a standard way to read the username, password, and roles for authentication.

// Note: This type of class is usually named as ModelName + "Detailes". But as I was a beginner, I thought of naming classes as per my convenience is cool.