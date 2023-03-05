package com.micro.userservice.UserServices.services;

import com.micro.userservice.UserServices.entities.User;

import java.util.List;

public interface UserService {
    //create user
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get user by id
    User getUser(String userId);


}
