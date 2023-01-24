package com.example.ecommerce.UserDto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CartDTO implements Serializable {


    private String cartId;
    private Customer customer;
    private boolean isGuest;
    private List<CartItem> cartItems;
    private Double totalOrderCost;
    private String createdBy;
    private String updatedBy;
    private Date dateTime;


}

