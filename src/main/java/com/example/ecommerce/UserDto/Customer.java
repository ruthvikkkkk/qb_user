package com.example.ecommerce.UserDto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
    private String userId;
    private String username;
    private String address;
    private String email;
}

