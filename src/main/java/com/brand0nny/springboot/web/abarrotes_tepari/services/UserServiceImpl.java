package com.brand0nny.springboot.web.abarrotes_tepari.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Address;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Role;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.AddressRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.RoleRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User createUser(User user) throws Exception {
        checkUsername(user);
        checkPassword(user);

        Optional<Role> optionalRoleUser = roleRepository.findByName("USER");
        Set<Role> roles = new HashSet<>();
        optionalRoleUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Debug logs
        System.out.println("User roles before save: " + user.getRoles());

        User savedUser = userRepository.save(user);

        // Debug logs
        System.out.println("User roles after save: " + savedUser.getRoles());

        return savedUser;
    }

    

    private boolean checkUsername(User user) throws Exception {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            throw new Exception("Username not available");
        }
        return true;
    }

    private boolean checkPassword(User user) throws Exception {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Password doesnt match");
        }
        return true;
    }

    @Override
    public Optional<User> getUser(User user) throws Exception {
        return userRepository.findByUsername(user.getUsername());
    }

    @Override
    public User getUserById(Long id) throws Exception {

        return userRepository.findById(id).orElseThrow(() -> new Exception("Id not found"));
    }

}
