package com.example.ecommerce.UserDto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDetails implements Serializable {

    private String productId;
    private String productName;
    private String merchantId;
    private String merchantName;
}
