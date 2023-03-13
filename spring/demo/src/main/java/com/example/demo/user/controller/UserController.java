package com.example.demo.user.controller;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.entity.UserCreateForm;
import com.example.demo.user.entity.UserUpdateForm;
import com.example.demo.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/join")
    public String join(UserCreateForm userCreateForm){return "join.html";}

    @GetMapping("/login")
    public String loginForm(){return "index.html";}

    @GetMapping("/update")
    public String userudpateForm(UserUpdateForm userUpdateForm, Principal principal, Model model){
        String loginid= principal.getName();
        UserDTO userDTO=this.userService.getUser(loginid);

        model.addAttribute("updateUser",userDTO);
        userUpdateForm.setUserid(userDTO.getUserid());
        userUpdateForm.setRole(userDTO.getRole());
        userUpdateForm.setUsername(userDTO.getUsername());
        userUpdateForm.setEmail(userDTO.getEmail());
        userUpdateForm.setPhone(userDTO.getPhone());
        userUpdateForm.setPassword(userDTO.getPassword());
        return "MyPage.html";
    }

    @GetMapping("/delete")
    public String userdelete(Principal principal,Model model){
        String loginid= principal.getName();
        UserDTO userDTO=this.userService.getUser(loginid);
        model.addAttribute("deleteUser",userDTO);

        this.userService.delete(userDTO);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String userupdateForm(@Valid UserUpdateForm userUpdateForm,Principal principal,Model model){
        String loginid= principal.getName();
        UserDTO userDTO=userService.getUser(loginid);

        model.addAttribute("updateUser",userDTO);
        this.userService.modify(userDTO,userUpdateForm.getUsername(),userUpdateForm.getUserid(),userUpdateForm.getPassword(),userUpdateForm.getRole(),userUpdateForm.getPhone(),userUpdateForm.getEmail());
        return "redirect:/";
    }

    @PostMapping("/joining")
    public String join(@Valid UserCreateForm userCreateForm,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "join.html";
        }
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "join.html";
        }
        try{
            userService.userdb(userCreateForm.getUsername(),userCreateForm.getUserid(),userCreateForm.getPassword1(),userCreateForm.getRole(),userCreateForm.getPhone(),userCreateForm.getEmail());
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "join.html";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "join.html";
        }
        return "redirect:/";
    }

    @RequestMapping("/user")
    public String userinfo(Principal principal,Model model){
        String loginid= principal.getName();
        UserDTO userDto=userService.getUser(loginid);
        model.addAttribute("user",userDto);

        return "user.html";
    }


}
