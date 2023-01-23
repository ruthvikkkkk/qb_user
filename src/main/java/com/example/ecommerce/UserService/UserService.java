package com.example.ecommerce.UserService;

import com.example.ecommerce.UserDto.SignInDto;
import com.example.ecommerce.UserDto.UpdateDto;
import com.example.ecommerce.UserDto.User;
import com.example.ecommerce.UserEntity.UserEntity;

import java.util.List;

public interface UserService {
//
//    boolean login(String email,String password);
    String signup(User user);

//    UserEntity updateUser(UserEntity user);
    UserEntity updateUsers(UpdateDto updateDto);
    List<UserEntity> findAll();
    UserEntity getUserById(int userId);
    String signIn(SignInDto signInDto);
}
