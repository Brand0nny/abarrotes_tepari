package com.brand0nny.springboot.web.abarrotes_tepari.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    @Query("SELECT a.id from Address a JOIN a.user u WHERE u.id = :userId")
    Optional<List<Long>> findAllAddressIdFromUserById(@Param("userId") Long userId);

}
