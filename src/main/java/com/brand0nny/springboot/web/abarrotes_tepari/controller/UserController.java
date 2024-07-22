package com.brand0nny.springboot.web.abarrotes_tepari.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Address;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.AddressRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.services.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping({ "/login", "/" })
    public String login() {

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            ModelMap model) throws Exception {

        if (result.hasErrors()) {
            model.addAttribute("user", user);
        }

        try {
            userService.createUser(user);
            System.out.println("==============================================" + user);
        } catch (Exception e) {
            System.out.println("============================================" + e.getMessage());
            model.addAttribute("formErrorMessage", e.getMessage());
            model.addAttribute("user", user);

        }

        return "register";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
