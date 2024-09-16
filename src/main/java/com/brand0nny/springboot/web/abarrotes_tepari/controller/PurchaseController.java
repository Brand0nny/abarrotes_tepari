package com.brand0nny.springboot.web.abarrotes_tepari.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Purchase;
import com.brand0nny.springboot.web.abarrotes_tepari.services.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    

    @PostMapping("/confirm-sell/notification/{id}")
    public ResponseEntity<Purchase> purchaseProduct(@PathVariable Long id){
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     UserDetails userDetails = (UserDetails) authentication.getPrincipal();
     String username =  userDetails.getUsername();
    return ResponseEntity.ok(purchaseService.confirmPurchase(id, username));
        
        
    }
}
