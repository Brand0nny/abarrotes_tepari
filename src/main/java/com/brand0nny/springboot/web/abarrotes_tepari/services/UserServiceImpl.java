package com.brand0nny.springboot.web.abarrotes_tepari.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brand0nny.springboot.web.abarrotes_tepari.dto.UserProfileDTO;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Address;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Role;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.AddressRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.ProductRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.RoleRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.UserRepository;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired 
    ProductRepository productRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    


  

    @Override
    public Optional<User> getUser(User user) throws Exception {
        return userRepository.findByUsername(user.getUsername());
    }

    @Override
    public User getUserById(Long id) throws Exception {

        return userRepository.findById(id).orElseThrow(() -> new Exception("Id not found"));
    }



    @Override
    public Optional<User> getUserByEmail(User user) throws Exception {
        return userRepository.findByEmail(user.getEmail());
    
    }
    @Override
    public boolean authenticateUser(User user) throws Exception {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            return true;
        }
        return false;
    }



    @Override
    public Optional<User> findByUsername(String username) {
     return userRepository.findByUsername(username);
       
    }

    @Override
    public Optional<UserProfileDTO> getDataByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()){
            UserProfileDTO userDto = new UserProfileDTO();
        User user = userOptional.get();
        userDto.setUsername(user.getUsername());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setAge(user.getAge());
        return Optional.of(userDto);
    }
    return Optional.empty();


    }
    @Override
    public Optional<String> getOwnerOfProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            Set<User> user = product.getUser();
            if(!user.isEmpty()){
        return user.stream().findFirst().map(User::getUsername);    
        }        

    }
    return Optional.empty();
    }

    @Override
    public Optional<Long> findIdByUsername(String username) {
        List<User> userList = userRepository.findIdByUsername(username);
        if(!userList.isEmpty()){
            Optional<Long> user = userList.stream().findFirst().map(User::getId);
            return user;
        }

        return Optional.empty();
    }

   
}

