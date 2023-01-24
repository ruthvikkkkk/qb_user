package com.example.ecommerce.UserDto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private String username;
    private String email;
    private Integer id;
    private String password;
    private String address1;
    private String state;
    private String mobile;
    private String city;
    private String confirmPassword;
}