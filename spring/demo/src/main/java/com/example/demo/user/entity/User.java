package com.example.demo.user.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String userid;

    private String password;

    private String role;

    private String phone;

    @Column(unique = true)
    private String email;

}
