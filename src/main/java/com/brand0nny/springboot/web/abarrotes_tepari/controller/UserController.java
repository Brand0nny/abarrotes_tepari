package com.brand0nny.springboot.web.abarrotes_tepari.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brand0nny.springboot.web.abarrotes_tepari.dto.ProductDTO;
import com.brand0nny.springboot.web.abarrotes_tepari.dto.UserProfileDTO;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Address;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import com.brand0nny.springboot.web.abarrotes_tepari.services.AddressService;
import com.brand0nny.springboot.web.abarrotes_tepari.services.ProductService;
import com.brand0nny.springboot.web.abarrotes_tepari.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  ProductService productService;
  @Autowired
  AddressService addressService;

  @GetMapping("/profile")
  public ResponseEntity<UserProfileDTO> all() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    Optional<UserProfileDTO> userProfile = userService.getDataByUsername(userDetails.getUsername());

    return userProfile.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }
  @Transactional
  @GetMapping("/my-products")
  public ResponseEntity<List<ProductDTO>> myProducts() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();

    return userService.findByUsername(username)
        .flatMap(u -> userService.findIdByUsername(username))
        .flatMap(productService::getProductsFromUserById)
        .map(productIds -> {
          List<ProductDTO> productDTOs = productIds.stream()
              .map(productService::getProductById)
              .filter(Optional::isPresent)
              .map(Optional::get)
              .map(Product::getId)
              .map(productService::getProductDto)
              .filter(Optional::isPresent)
              .map(Optional::get)
              .collect(Collectors.toList());
          return ResponseEntity.ok(productDTOs);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

 
  }

  @PostMapping("/add-address")
  public ResponseEntity<Address> createAddress(@RequestBody Address address) throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();
    Address createAddress = addressService.createAddress(address, username);
    return new ResponseEntity<>(createAddress, HttpStatus.CREATED);

  }

  @GetMapping("/my-addresses")
  public ResponseEntity<List<Address>> getMyAddresses() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    return userService.findIdByUsername(userDetails.getUsername())
    .flatMap(u->addressService.getAllAddressFromUser(u))
    .map(addressIds-> {
      List<Address> addressStorage = addressIds.stream()
      .map(addressService::getAddressById)
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(Collectors.toList());
      return ResponseEntity.ok(addressStorage);
    }).orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    

    

    }
  

}