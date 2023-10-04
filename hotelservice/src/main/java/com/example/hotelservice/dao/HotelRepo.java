package com.example.hotelservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotelservice.modle.Hotel;

public interface HotelRepo extends JpaRepository<Hotel,String> {
    
}
