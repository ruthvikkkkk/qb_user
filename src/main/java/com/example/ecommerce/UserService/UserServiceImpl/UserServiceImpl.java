package com.example.ecommerce.UserService.UserServiceImpl;
import com.example.ecommerce.UserDto.SignInDto;
import com.example.ecommerce.UserDto.UpdateDto;
import com.example.ecommerce.UserDto.User;
import com.example.ecommerce.UserEntity.UserEntity;
import com.example.ecommerce.UserRepository.UserRepository;
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



    //
//        @Override
//    public boolean login(String email, String password) {
//
//        List<UserEntity> userEntities=userRepository.findAll();
//        System.out.println(userEntities);
//        for(UserEntity userEntity:userEntities){
//            if (userEntity.getPassword().equals(password) && userEntity.getEmail().equals(email)) {
//                return true;
//            }
//        }
//        return false;
//    }
    @Override
    public String signup(User user) {
        if (Objects.nonNull(userRepository.findByEmail(user.getEmail()))) {
            return "email already exists";
        }

        if (!user.getPassword().equals(user.getConfirmpassword())) {
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

        userRepository.save(userEntity);
        return "created succsessfully";

//        String encryptedPassword = user.getPassword();
//        try {
//            encryptedPassword = hashPassword(user.getPassword());
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        String encryptedConfirmPassword = user.getConfirmpassword();
//        try {
//            encryptedPassword = hashPassword(user.getConfirmpassword());
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername(user.getUsername());
//        userEntity.setEmail(user.getEmail());
//        userEntity.setPassword(encryptedPassword);
//        userEntity.setConfirmpassword(encryptedConfirmPassword);
//
//        List<UserEntity> userEntities = userRepository.findByEmail(user.getEmail());
//        List<UserEntity> userEntities1 = userRepository.findByEmail(user.getUsername());
//        if (userEntities.size() == 0) {
//            if (userEntity.getPassword().equals(userEntity.getConfirmpassword())) {
//                userRepository.save(userEntity);
//                return true;
//            }
//        }
//        return false;


    }

//    @Override
//    public UserEntity updateUser( UserEntity userEntity) {
//
//        for(int i = 0; i < users.size(); i++) {
//
//            User u = users.get(i);
//
//            if(u.getId().equals(id)) {
//
//                users.set(i, user);
//
//                return;
//            }
//        }

//    @Override
//    public Integer byid(String email, String password) {
//        Iterable<UserEntity> userEntities = userRepository.findAll();
//        for (UserEntity userEntity : userEntities) {
//
//            if (userEntity.getPassword().equals(password)&& userEntity.getEmail().equals(email)) {
//                Integer id=userEntity.getId();
//                return id;
//
//
//            }
//        }
//        return null;
//    }


    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }




    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList;
    }

//
//    @Override
//    public Integer byid(String email, String password) {
//        Iterable<UserEntity> userEntities = userRepository.findAll();
//        for (UserEntity userEntity : userEntities) {
//
//            if (userEntity.getPassword().equals(password)&& userEntity.getEmail().equals(email)) {
//                Integer id=userEntity.getId();
//                return id;
//
//
//            }
//        }
//        return null;
//    }
public String signIn(SignInDto signInDto) {
    UserEntity userEntity= userRepository.findByEmail(signInDto.getEmail());

    if(Objects.isNull(userRepository.findByEmail(signInDto.getEmail()))) {
        return "username does not exist";
    }

    try {
        if (!userEntity.getPassword().equals(hashPassword(signInDto.getPassword())))
            return "wrong password";
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }

    return "success";
}


//
//    @Override
//    public List<User> get() {
//        Iterable<UserEntity> userEntities=userRepository.findAll();
//        List<User> userList=new ArrayList<>();
//        for(UserEntity userEntity:userEntities){
//            User product=new User(userEntity.getId(),userEntity.getEmail(),userEntity.getPassword(),
//                    userEntity.getUsername(),userEntity.getAddress1(),userEntity.getCity(),userEntity.getMoblieno(),
//                    userEntity.getState());
//            userList.add(product);
//        }
//
//        return userList;
//    }
@Override
public UserEntity updateUsers(UpdateDto updateDto) {
        UserEntity userEntity = new UserEntity();
    Optional < UserEntity > user = this.userRepository.findById(userEntity.getId());

    if (user.isPresent()) {
        UserEntity userEntity1 = user.get();
        userEntity1.setId(userEntity.getId());
        userEntity1.setMoblieno(userEntity.getMoblieno());
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

}
