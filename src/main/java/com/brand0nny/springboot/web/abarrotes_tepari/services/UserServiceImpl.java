package com.brand0nny.springboot.web.abarrotes_tepari.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Address;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.AddressRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(User user) throws Exception {
        if(checkUsername(user)&&checkPassword(user)){
            
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }     
        return userRepository.save(user);
    }

    

    private boolean checkUsername(User user) throws Exception{
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if(userOptional.isPresent()){
            throw new Exception("Username not available");
        }
        return true;
    }

    private boolean checkPassword(User user) throws Exception{
        if(!user.getPassword().equals(user.getConfirmPassword())){
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
        

        return userRepository.findById(id).orElseThrow(()-> new Exception("Id not found"));
    }




  

}
