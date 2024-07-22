package com.brand0nny.springboot.web.abarrotes_tepari.services;

import java.util.Optional;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Address;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;

public interface UserService {

public User createUser(User user) throws Exception;

public Optional<User> getUser(User user) throws Exception;

public User getUserById(Long id) throws Exception;

}
