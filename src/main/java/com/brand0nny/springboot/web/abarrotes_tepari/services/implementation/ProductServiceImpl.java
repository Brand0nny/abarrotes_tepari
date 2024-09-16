package com.brand0nny.springboot.web.abarrotes_tepari.services.implementation;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brand0nny.springboot.web.abarrotes_tepari.dto.ProductDTO;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Purchase;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.ProductRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.UserRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.services.ProductService;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductDTO> getProductDto(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductDTO productDTO = new ProductDTO();
            Product product = optionalProduct.get();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setDate(product.getDate());
            productDTO.setImageUrl(product.getImageUrl());
            productDTO.setSeller(product.getSeller().getUsername());
            return Optional.of(productDTO);

        }
        return Optional.empty();

    }

    @Override
    public Product createProduct(Product product, String username) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(new Date());
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Product newProduct = Product.builder()
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .date(formattedDate)
                    .imageUrl(product.getImageUrl())
                    .availableQuantity(product.getAvailableQuantity())
                    .seller(user)
                    .build();
            Product savedProduct = productRepository.save(newProduct);

            user.getProductsForSale().add(savedProduct);
            userRepository.save(user);
            return savedProduct;
        }
        throw new RuntimeException("user not found");
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean deleteProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Optional<List<Long>> getProductsFromUserById(Long id) {

        return null;
    }

    @Override
    public Set<Product> getAllProductsFromUserId(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Set<Product> products = user.getProductsForSale();
            return products;
        }
        throw new RuntimeException("ID does not exist");
    }

    @Override
    public Set<Purchase> getAllBuyedProductsFromUserId(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Set<Purchase> purchase = user.getPurchases();
            return purchase;
        }
        throw new RuntimeException("ID does not exist");

    }

}
