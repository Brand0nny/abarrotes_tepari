package com.brand0nny.springboot.web.abarrotes_tepari.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long>{
    Optional<Role> findByName(String name);

}
