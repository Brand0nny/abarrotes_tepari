package com.brand0nny.springboot.web.abarrotes_tepari.services;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Purchase;

public interface PurchaseService {

    public Purchase confirmPurchase(Long notificationId, String sellerUsername);
    
} 
