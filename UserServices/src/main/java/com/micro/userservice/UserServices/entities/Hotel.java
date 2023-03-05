package com.micro.userservice.UserServices.entities;

import lombok.Data;

@Data
public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private String about;
}
