package com.brand0nny.springboot.web.abarrotes_tepari.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Purchase;
import com.brand0nny.springboot.web.abarrotes_tepari.services.UserService;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long>{


}
