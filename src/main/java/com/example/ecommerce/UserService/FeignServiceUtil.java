package com.example.ecommerce.UserService;

import com.example.ecommerce.UserDto.CartDTO;
import com.example.ecommerce.UserDto.CartItem;
import com.example.ecommerce.UserEntity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "userFeignToCart", url = "http://10.20.2.188:8084/cartController")
public interface FeignServiceUtil {

    @PostMapping("/createCart")
    CartDTO createCart(@RequestBody CartDTO cart);

    @PostMapping("/addToCart/{cartId}")
    List<CartItem> addToCart(@PathVariable("cartId") String cartId, @RequestBody CartItem cartItem);

    @DeleteMapping("/deleteOneItem/{cartId}/{skuId}")
    List<CartItem> deleteOneItem(@PathVariable("cartId") String cartId, @PathVariable("skuId") String skuId);

    @GetMapping("/findAllInCart/{cartId}")
    List<CartItem> findAllInCart(@PathVariable("cartId") String cartId);

    @PostMapping("/buy/{cartId}")
    Order createOrder(@PathVariable("cartId") String cartId);
}