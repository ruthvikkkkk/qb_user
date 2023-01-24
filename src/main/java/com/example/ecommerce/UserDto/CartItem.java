package com.example.ecommerce.UserDto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItem implements Serializable {

    private String SKU_ID;
    private ProductDetails productDetails;
    private Double productPrice;
    private Double quantity;

}
