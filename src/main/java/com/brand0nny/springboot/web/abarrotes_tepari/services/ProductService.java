package com.brand0nny.springboot.web.abarrotes_tepari.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.brand0nny.springboot.web.abarrotes_tepari.dto.ProductDTO;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Purchase;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;

public interface ProductService {
public Iterable<Product> getAllProducts();
public Optional<ProductDTO> getProductDto(Long id);
public Product createProduct(Product product, String username);
public Optional<Product> getProductById(Long id);
public boolean deleteProductById(Long id);
public Optional<List<Long>> getProductsFromUserById(Long id);
public Set<Product> getAllProductsFromUserId(Long id);
public Set<Purchase> getAllBuyedProductsFromUserId(Long id);
} 