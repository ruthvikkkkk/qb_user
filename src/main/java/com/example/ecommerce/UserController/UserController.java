package com.example.ecommerce.UserController;
import com.example.ecommerce.Response.Response;
import com.example.ecommerce.UserDto.SignInDto;
import com.example.ecommerce.UserDto.UpdateDto;
import com.example.ecommerce.UserDto.User;
//import com.example.ecommerce.UserDto.UserUpdateDTO;
import com.example.ecommerce.UserEntity.UserEntity;
import com.example.ecommerce.UserService.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Valid
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(path="/get")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = new ArrayList<>();
        for(UserEntity userEntity :userService.findAll()){
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            userList.add(user);
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping(value = "signIn")
    public ResponseEntity<String> signIn(@RequestBody SignInDto signInDto) {

        int response = userService.signIn(signInDto);
        if(response == 1)
            return new ResponseEntity<>("Logged In Successfully!", HttpStatus.OK);
        else if(response == 2)
            return new ResponseEntity<>("E-mail does not exist!", HttpStatus.OK);
        else if(response == 3)
            return new ResponseEntity<>("Password Incorrect!", HttpStatus.OK);
        return  null;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {

        return new ResponseEntity<>(userService.signUp(user), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }


@PostMapping("/updateUsers")
public ResponseEntity<UserEntity> updateUser(@RequestBody UpdateDto updateDto){
    UserEntity userEntity = userService.getUserById(updateDto.getId());
    userEntity.setAddress1(updateDto.getAddress1());
    userEntity.setCity(updateDto.getCity());
    userEntity.setEmail(updateDto.getEmail());
    userEntity.setMobile(updateDto.getMobile());
    return new ResponseEntity<>(userEntity, HttpStatus.OK);
}


}