package com.brand0nny.springboot.web.abarrotes_tepari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brand0nny.springboot.web.abarrotes_tepari.config.jwt.JwtService;
import com.brand0nny.springboot.web.abarrotes_tepari.payload.request.LoginRequest;
import com.brand0nny.springboot.web.abarrotes_tepari.payload.request.RegisterRequest;
import com.brand0nny.springboot.web.abarrotes_tepari.payload.response.AuthResponse;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.UserRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
     @Autowired
     AuthService authService;
     UserDetails userDetails;
     @Autowired
     JwtService jwtService;
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        
        return ResponseEntity.ok(authService.register(request));


        
    }

   

}
