package com.example.demo.user.dto;

import com.example.demo.user.entity.User;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String userid;
    private String password;
    private String role;
    private String phone;
    private String email;


}
