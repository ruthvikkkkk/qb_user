package com.example.ecommerce.UserEntity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private Integer userId;


    private String email;
    private String username;
    private String password;
    private String address1;
    private String state;
    private String mobile;
    private String city;
}
