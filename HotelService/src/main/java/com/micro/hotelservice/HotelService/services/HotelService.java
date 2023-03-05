package com.micro.hotelservice.HotelService.services;

import com.micro.hotelservice.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel get(String id);
}
