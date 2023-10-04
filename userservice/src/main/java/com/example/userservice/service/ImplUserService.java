package com.example.userservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.userservice.dao.UserRepo;
import com.example.userservice.exceptions.ResourceNotFoundException;
import com.example.userservice.external.HotelService;
import com.example.userservice.external.RatingService;
import com.example.userservice.model.Hotel;
import com.example.userservice.model.Rating;
import com.example.userservice.model.User;

@Service
public class ImplUserService implements IuserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        User savedUser = userRepo.save(user);
        return savedUser;

    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User getSingleUser(String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with given id is Not Found on the server: " + userId));

        // http://localhost:8083/ratings/user/d24b4556-2177-4f89-bd46-4fc04a8b58e0

        // Rating[] userRatings = restTemplate.getForObject(
        // "http://RATING-SERVICE/ratings/user/" + user.getUserId(), Rating[].class);

        List<Rating> ratingsList = ratingService.getRatings(user.getUserId());

        // List<Rating> ratingsList = Arrays.stream(userRatings).toList();

        List<Rating> userRatingsList = ratingsList.stream().map(rating -> {

            // ResponseEntity<Hotel> forEntity = restTemplate
            // .getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(),
            // Hotel.class);

            // Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(userRatingsList);

        return user;
    }

}
