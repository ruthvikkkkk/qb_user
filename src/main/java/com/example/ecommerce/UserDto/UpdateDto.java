package com.example.ecommerce.UserDto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateDto {

    private int id;
    private String email;
    private String name;
    private String address1;
    private String city;
    private String state;
    private String mobile;
}