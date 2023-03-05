package com.micro.hotelservice.HotelService.services.impl;

import com.micro.hotelservice.HotelService.entities.Hotel;
import com.micro.hotelservice.HotelService.exception.ResourceNotFoundException;
import com.micro.hotelservice.HotelService.repositories.HotelRepository;
import com.micro.hotelservice.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        hotel.setHotelId(UUID.randomUUID().toString());
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel with given id is not found!!"));
    }
}
