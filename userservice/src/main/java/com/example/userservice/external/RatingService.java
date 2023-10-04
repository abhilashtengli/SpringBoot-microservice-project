package com.example.userservice.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.userservice.model.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    
    @GetMapping("/ratings/user/{userId}")
    List<Rating> getRatings(@PathVariable String userId);
}
