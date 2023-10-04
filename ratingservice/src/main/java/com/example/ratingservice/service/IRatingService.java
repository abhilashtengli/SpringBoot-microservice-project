package com.example.ratingservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ratingservice.modle.Rating;

@Service
public interface IRatingService {

    // create
    Rating addRating(Rating rating);

    //get All
    List<Rating> getAllRating();

    // get by user id
    List<Rating> getRatingByUserId(String userId);

    // get by hotel id
    List<Rating> getRatingByHotelId(String hotelId);
}
