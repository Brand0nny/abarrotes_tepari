package com.brand0nny.springboot.web.abarrotes_tepari.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;
import com.brand0nny.springboot.web.abarrotes_tepari.services.ProductService;
import com.brand0nny.springboot.web.abarrotes_tepari.services.UserService;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @GetMapping(value="/")
    public ResponseEntity<Iterable<Product>> products(){
    return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        if(product.getIsBuyed().equals(false)){

            return new ResponseEntity<>(productService.createProduct(product, username), HttpStatus.CREATED);
        } return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("/delete-product/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> delProduct(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<String> usernameOwner = userService.getOwnerOfProductById(id);
        if(usernameOwner.equals(userDetails.getUsername())){

        return ResponseEntity.ok(productService.deleteProductById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> seeProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }



}
