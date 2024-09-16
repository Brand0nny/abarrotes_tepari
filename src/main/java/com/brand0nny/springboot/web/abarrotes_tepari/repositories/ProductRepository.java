package com.brand0nny.springboot.web.abarrotes_tepari.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{



}
