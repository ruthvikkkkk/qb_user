package com.example.ecommerce.UserService;

import com.example.ecommerce.UserDto.CartItem;
import com.example.ecommerce.UserDto.SignInDto;
import com.example.ecommerce.UserDto.UpdateDto;
import com.example.ecommerce.UserDto.User;
import com.example.ecommerce.UserEntity.UserEntity;

import java.util.List;

public interface UserService {

    String signUp(User user);
    UserEntity updateUser(UpdateDto updateDto);
    List<UserEntity> findAll();
    UserEntity getUserById(int userId);
    UserEntity getUserByEmail(String email);
    Integer signIn(SignInDto signInDto);
    List<CartItem> addToCart(String cartId, CartItem cartItem);
}
