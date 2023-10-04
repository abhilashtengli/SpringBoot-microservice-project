package com.example.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.userservice.model.User;

@Service
public interface IuserService {

    // create user
    public User saveUser(User user);

    // get All user

    public List<User> getAll();

    // get single user

    public User getSingleUser(String userId);
}
