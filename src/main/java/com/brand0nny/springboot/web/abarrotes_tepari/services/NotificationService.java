package com.brand0nny.springboot.web.abarrotes_tepari.services;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Notification;

public interface NotificationService {

    public Notification sendPurchaseRequest(String username, Long productId, int quantity);
}
