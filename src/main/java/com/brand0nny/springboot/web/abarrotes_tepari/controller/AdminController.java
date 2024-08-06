package com.brand0nny.springboot.web.abarrotes_tepari.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import com.brand0nny.springboot.web.abarrotes_tepari.services.UserService;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/admin/users-management")
public class AdminController {
    @Autowired
    UserService userService;
    
    @GetMapping({"/",""} )
    public ResponseEntity<Iterable<User>> userManagement(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/delete-user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long userId){
    userService.deleteUserById(userId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/edit-user/{id}")
    public ResponseEntity<User> editUserUsers(@PathVariable("id") Long userId) throws Exception{
        
        
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    @PostMapping("/edit-user/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") Long userId, @RequestBody User editedUser){
        userService.editUser(userId, editedUser);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        userService.createUser(newUser);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
