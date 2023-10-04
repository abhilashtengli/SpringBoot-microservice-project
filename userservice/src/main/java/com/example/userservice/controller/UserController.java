package com.example.userservice.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.model.User;
import com.example.userservice.service.ImplUserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    ImplUserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    int retryCount = 1;

    @GetMapping("{userId}")
    // main method
    // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod =
    // "ratingHotelFallback")
    // @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    // @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        logger.info("returyCount : {}", retryCount);
        retryCount++;
        User singleUser = userService.getSingleUser(userId);
        return ResponseEntity.ok(singleUser);
    }

    // The return type of the main method and fallback method should be same.
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        User user = User.builder()
                .UserEmail("abc@gmail.com")
                .UserName("Dummy")
                .about("Some service is down so this user is created")
                .UserId(userId)
                .build();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUsers = userService.getAll();
        return ResponseEntity.ok(allUsers);
    }
}