package com.brand0nny.springboot.web.abarrotes_tepari.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}
