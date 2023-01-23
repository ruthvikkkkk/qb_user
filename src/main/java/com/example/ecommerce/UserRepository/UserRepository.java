package com.example.ecommerce.UserRepository;

import com.example.ecommerce.UserEntity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    UserEntity findByEmail(String email);

    List<UserEntity> findAll();
}