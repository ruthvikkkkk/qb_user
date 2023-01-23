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
//
//    @Autowired
//    ContactService contactService;


    @GetMapping(path="/get")
    public List<User> display(){
            List<UserEntity> userList = userService.findAll();
            List<User> users = new ArrayList<>();
            for (UserEntity userEntity : userList) {
                User user = new User();
                BeanUtils.copyProperties(userEntity, user);
                users.add(user);
            }
            return users;
        }



//    @GetMapping(path="/userlogin" )
//    public Response<Boolean> userlogin2(@RequestParam String email, String password) {
//
//        Boolean isTrue = userService.login(email, password);
//        if (isTrue) {
//            return new Response<>(true);
//
//        } else {
//            return new Response<>(6001, "unable to login");
//        }
//
//    }

    @PostMapping(value = "signIn")
    public String signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }

    @PostMapping(value = "/signup")
    public String signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }


//    @RequestMapping(value="/users/{id}", method = RequestMethod.PUT)
//    public void getUser(@PathVariable String id, @RequestBody UserEntity userEntity) {
//
//        userService.updateUser(id, userEntity);
//
//    }
//    @PutMapping("/instructors/{id}")
//    public Response<User> updateUser(@PathVariable long id, @RequestBody User user) {
//        user.setId(id);
//        return Response.ok().body(this.userService.updateUser(user));
//    }

//    @CrossOrigin(origins = "http://localhost:8080/")
//    @PostMapping(path="/usersignup" ,produces = "application/json")
//    public String usersignup(@RequestBody @Valid User user){
//        return userService.signup(user);
//
//    }

//    @CrossOrigin(origins = "http://localhost:8080/")
//    @PostMapping(path="/contact" ,produces = "application/json")
//    public void contact(@RequestBody @Valid Contact contact){
//        contactService.submit(contact);
//    }


//    @CrossOrigin(origins = "http://localhost:8080/")
//    @GetMapping(path="/byid" ,produces = "application/json")
//    public Integer byid(@RequestParam @Valid String email,String password){
//        return userService.byid(email,password);
//    }
//@PostMapping("/updateStock")
//public Response<User> updateStock(@RequestBody UserUpdateDTO userUpdateDTO){
//    User user = userService.getStock(userUpdateDTO.getSkuId());
//    user.setPrice(stockUpdateDTO.getPrice());
//    user.setQuantity(stockUpdateDTO.getQuantity());
//    UserkDto stockDto = new StockDto();
//    BeanUtils.copyProperties(stock, stockDto);
//    return new ResponseEntity<>(stockService.addStock(stockDto), HttpStatus.OK);
//}

@PostMapping("/updateUsers")
public ResponseEntity<UserEntity> updateUser(@RequestBody UpdateDto updateDto){
    UserEntity userEntity = userService.getUserById(updateDto.getId());
    userEntity.setAddress1(updateDto.getAddress1());
    userEntity.setCity(updateDto.getCity());
    userEntity.setEmail(updateDto.getEmail());
    userEntity.setMoblieno(updateDto.getMoblieno());
    return new ResponseEntity<>(userEntity, HttpStatus.OK);
}


}