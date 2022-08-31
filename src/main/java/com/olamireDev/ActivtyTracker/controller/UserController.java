package com.olamireDev.ActivtyTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.olamireDev.ActivtyTracker.DTO.UserDTO;
import com.olamireDev.ActivtyTracker.model.User;
import com.olamireDev.ActivtyTracker.serviceImpl.UserServiceImpl;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping(value = "/login")
    public String getUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model m){
        UserDTO user =userServiceImpl.getUser(email, password);
        if(user != null){
            session.setAttribute("user", user);
            return "redirect:/home";
        }
        m.addAttribute("error", "invalid credentials provided try again");
        return "login";
    }

    @GetMapping("/register")
    public String signUp(Model m){
        m.addAttribute("using", new User());
        return "signUp";
    }

    @PostMapping(value ="/registerU")
    public String signUpUser(@ModelAttribute("using") User user, HttpSession session, Model m){
        if(user.getPassword().equals(user.getPassConfirm())){
            if(userServiceImpl.validEmail(user.getEmail()) == 0){
                UserDTO loggedIn = userServiceImpl.addUser(user);
                if(loggedIn != null){
                    session.setAttribute("user", loggedIn);
                    return "redirect:/home";
                }
                m.addAttribute("error", "something went wrong please try again");
            }else{
                m.addAttribute("error", "Email already in use ");
            }
        }else {
            m.addAttribute("error","passwords do not match");
        }
        return "login";
    }
}