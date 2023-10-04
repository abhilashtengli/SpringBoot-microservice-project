package com.example.hotelservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotelservice.modle.Hotel;

@Service
public interface IHotelService {

    // create
    Hotel saveHotel(Hotel hotel);

    // getAll
    List<Hotel> getAllHotels();

    // getById

    Hotel getHotelById(String id);

}
