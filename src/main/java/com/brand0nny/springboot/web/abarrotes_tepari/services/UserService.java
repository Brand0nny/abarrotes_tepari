package com.brand0nny.springboot.web.abarrotes_tepari.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.brand0nny.springboot.web.abarrotes_tepari.dto.UserProfileDTO;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Address;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;

public interface UserService {
public User createUser(User user);

public Iterable<User> getAllUsers();

public User editUser(Long id, User editedUser) throws UsernameNotFoundException;

public Optional<User> getUser(User user) throws Exception;

public void deleteUserById(Long id);

public Optional<User> getUserByEmail(User user) throws Exception;
 
public User getUserById(Long id) throws Exception;


public boolean authenticateUser(User user) throws Exception;

public Optional<User> findByUsername(String username);

public Optional<UserProfileDTO> getDataByUsername(String username);
public Optional<String> getOwnerOfProductById(Long id);
public Optional<Long> findIdByUsername(String username);

}
