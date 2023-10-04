package com.example.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingservice.modle.Rating;
import com.example.ratingservice.service.ImplRatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private ImplRatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating addedRating = ratingService.addRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRating);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating() {
        List<Rating> allRatings = ratingService.getAllRating();
        return ResponseEntity.ok(allRatings);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
        List<Rating> userRatings = ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(userRatings);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
        List<Rating> hotelRatings = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(hotelRatings);
    }
}
