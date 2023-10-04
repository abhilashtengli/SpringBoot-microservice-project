package com.example.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.model.User;

public interface UserRepo extends JpaRepository<User,String> {
    
}
