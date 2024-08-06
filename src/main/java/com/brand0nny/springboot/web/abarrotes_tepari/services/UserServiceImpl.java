package com.brand0nny.springboot.web.abarrotes_tepari.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @Override
    public User createUser(User user) {
    User newUser = User.builder()
    .username(user.getUsername())
    .firstname(user.getFirstname())
    .lastname(user.getLastname())
    .email(user.getEmail())
    .password(user.getPassword())
    .age(user.getAge())
    .build();

    Optional<Role> roleReOptional = roleRepository.findByName("ROLE_USER");
    Set<Role> roles = new HashSet<>();
    roleReOptional.ifPresent(roles::add);

    user.setRoles(roles);
    return userRepository.save(newUser);
    
    } 

    @Override
    public Iterable<User> getAllUsers() {
        System.out.println(userRepository.findAll().toString());
        return userRepository.findAll();
    }
    
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
        userDto.setRole(user.getRoles());
        System.out.println(userDto.toString());
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

    @Override
    public void deleteUserById(Long id) {
       userRepository.deleteById(id);
  
    }

    @Override
    public User editUser(Long id, User editedUser) throws UsernameNotFoundException {
        return userRepository.findById(id).map(
            user -> { 
            user.setUsername(editedUser.getUsername());
            user.setFirstname(editedUser.getFirstname());
            user.setLastname(editedUser.getLastname());
            user.setEmail(editedUser.getEmail());
            user.setAge(editedUser.getAge());
            user.setPassword(editedUser.getPassword());
            return userRepository.save(user);
            }
        ).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        
        
    }

    }


   


