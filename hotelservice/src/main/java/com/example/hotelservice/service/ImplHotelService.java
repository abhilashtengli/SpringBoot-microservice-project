package com.example.hotelservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotelservice.dao.HotelRepo;
import com.example.hotelservice.exceptions.ResourceNotFoundException;
import com.example.hotelservice.modle.Hotel;

@Service
public class ImplHotelService implements IHotelService {

    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setId(randomId);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {

        return hotelRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with given id not found :" + id));
    }

}
