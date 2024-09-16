package com.brand0nny.springboot.web.abarrotes_tepari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brand0nny.springboot.web.abarrotes_tepari.dto.ProductRequestDTO;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Notification;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Purchase;
import com.brand0nny.springboot.web.abarrotes_tepari.services.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
@Autowired
NotificationService notificationService;
    @PostMapping("/request-product/{id}")
    public ResponseEntity<Notification> requestProduct(@RequestBody ProductRequestDTO productRequestDTO, @PathVariable Long id){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails =(UserDetails) authentication.getPrincipal();
    String username =  userDetails.getUsername();
    
    int quantity = productRequestDTO.getQuantity();

    return ResponseEntity.ok(notificationService.sendPurchaseRequest(username, id, quantity));

    }

}
