package com.example.ecommerce.UserEntity;

import com.example.ecommerce.UserDto.CartItem;
import com.example.ecommerce.UserDto.Customer;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private String orderId;
    private String cartId;
    private Customer customer;
    private List<CartItem> cartItems;
    private Double totalOrderCost;
    private String createdBy;
    private String updatedBy;
    private Date dateTime;
}

