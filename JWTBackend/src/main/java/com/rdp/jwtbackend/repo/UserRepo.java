package com.rdp.jwtbackend.repo;

import com.rdp.jwtbackend.model.UsersModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UsersModel, Integer> {
    UsersModel findByUsername(String username);   // returns entity, not Optional
}

// "loadUserByUsername" needs to fetch the user by username. JPA doesn’t provide a built-in method for that. So we add this line in the class.
