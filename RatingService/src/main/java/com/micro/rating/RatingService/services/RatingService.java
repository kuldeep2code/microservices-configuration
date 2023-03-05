package com.micro.rating.RatingService.services;

import com.micro.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    //create rating
    Rating create(Rating rating );


    //get all ratings
    List<Rating> getAllRatings();


    //get all by userId
    List<Rating> getRatingByUserId(String userId);


    //get all by hotelId
    List<Rating> getRatingByHotelId(String hotelId);
}
