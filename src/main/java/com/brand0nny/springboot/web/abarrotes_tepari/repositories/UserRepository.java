package com.brand0nny.springboot.web.abarrotes_tepari.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findIdByUsername(String username);
    
}
