package com.brand0nny.springboot.web.abarrotes_tepari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import com.brand0nny.springboot.web.abarrotes_tepari.services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/login","/"})
    public String login(){
        
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userReg", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("userReg") User user) throws Exception{
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(){

        
        return "home";
    }



}
