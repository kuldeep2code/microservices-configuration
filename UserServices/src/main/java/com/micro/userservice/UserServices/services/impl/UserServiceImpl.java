package com.micro.userservice.UserServices.services.impl;

import com.micro.userservice.UserServices.entities.Hotel;
import com.micro.userservice.UserServices.entities.Rating;
import com.micro.userservice.UserServices.entities.User;
import com.micro.userservice.UserServices.exception.ResourceNotFoundException;
import com.micro.userservice.UserServices.repositries.UserRepository;
import com.micro.userservice.UserServices.services.UserService;
import com.micro.userservice.UserServices.services.external.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server!! : ID- "+userId));

        Rating[] template = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}",template);
        List<Rating> ratings = Arrays.stream(template).toList();

        final List<Rating> ratingList = ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            logger.info("{}", forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating ;
        }).collect(Collectors.toList());

        user.setRating(ratingList);

        return user;
    }
}
