package com.example.ecommerce.UserService.UserServiceImpl;
import com.example.ecommerce.UserDto.*;
import com.example.ecommerce.UserEntity.UserEntity;
import com.example.ecommerce.UserRepository.UserRepository;
import com.example.ecommerce.UserService.FeignServiceUtil;
import com.example.ecommerce.UserService.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeignServiceUtil userFeignClient;

    @Override
    public String signUp(User user) {
        if (Objects.nonNull(userRepository.findByEmail(user.getEmail()))) {
            return "email already exists";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "password does not match";
        }

        String encryptedPassword = user.getPassword();
        try {
            encryptedPassword = hashPassword(user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setPassword(encryptedPassword);

        CartDTO cartDTO = new CartDTO();
        Customer customer = new Customer();
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(Double.parseDouble("0"));
        cartItem.setSKU_ID("");
        cartItem.setProductDetails(new ProductDetails());
        cartItem.setProductPrice(Double.parseDouble("0"));
        BeanUtils.copyProperties(userEntity, customer);
        cartDTO.setCustomer(customer);
        cartDTO.setGuest(false);
        cartDTO.setCartItems((new ArrayList<CartItem>()));
        //cartDTO.getCartItems().add(cartItem);
        userFeignClient.createCart(cartDTO);
        userRepository.save(userEntity);
        return "created succsessfully";
    }


    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Integer signIn(SignInDto signInDto) {
        UserEntity userEntity= userRepository.findByEmail(signInDto.getEmail());

        if(Objects.isNull(userRepository.findByEmail(signInDto.getEmail()))) {
            return 2;
        }

        try {
            if (!userEntity.getPassword().equals(hashPassword(signInDto.getPassword())))
                return 3;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return 1;
    }

    @Override
    public List<CartItem> addToCart(String cartId, CartItem cartItem) {
        return userFeignClient.addToCart(cartId, cartItem);
    }


    @Override
    public UserEntity updateUser(UpdateDto updateDto) {
            UserEntity userEntity = new UserEntity();
        Optional <UserEntity> user = userRepository.findById(userEntity.getUserId());

        if (user.isPresent()) {
            UserEntity userEntity1 = user.get();
            userEntity1.setUserId(userEntity.getUserId());
            userEntity1.setMobile(userEntity.getMobile());
            userEntity1.setEmail(userEntity.getEmail());
            userRepository.save(userEntity1);
            return userEntity1;
        }
        return null;
    }

    @Override
    public UserEntity getUserById(int userId) {

        Optional<UserEntity> user = this.userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
