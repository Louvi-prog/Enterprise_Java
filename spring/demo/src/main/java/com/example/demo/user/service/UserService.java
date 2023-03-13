package com.example.demo.user.service;

import com.example.demo.DataNotFoundException;
import com.example.demo.user.Repository.UserRepository;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;


    private UserDTO of(User user) {
        return this.modelMapper.map(user, UserDTO.class);
    }

    private User of(UserDTO userDTO) {
        return this.modelMapper.map(userDTO,User.class);
    }

    public UserDTO userdb(String username, String userid, String password, String role, String phone, String email) {
        User user = new User();
        user.setUsername(username);
        user.setUserid(userid);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole(role);
        user.setPhone(phone);
        this.userRepository.save(user);
        return of (user);
    }


    public UserDTO getUser(String userid) {
        Optional<User> user = this.userRepository.findByUserid(userid);
        if (user.isPresent()) {
            return of(user.get());
        } else {
            throw new DataNotFoundException("user not found");
        }
    }

    public UserDTO modify(UserDTO userDTO,String username, String userid, String password, String role, String phone, String email) {
        userDTO.setUsername(username);
        userDTO.setUserid(userid);
        userDTO.setPassword(passwordEncoder.encode(password));
        userDTO.setRole(role);
        userDTO.setPhone(phone);
        userDTO.setEmail(email);
        this.userRepository.save(of(userDTO));
        return userDTO;
    }

    public void delete(UserDTO userdto){
        userRepository.deleteById(userdto.getId());
    }


}
