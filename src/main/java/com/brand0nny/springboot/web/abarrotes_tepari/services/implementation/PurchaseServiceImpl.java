package com.brand0nny.springboot.web.abarrotes_tepari.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Notification;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Purchase;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.NotificationRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.ProductRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.PurchaseRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.UserRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.services.PurchaseService;
import com.brand0nny.springboot.web.abarrotes_tepari.services.UserService;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    NotificationRepository notificationRepository;

    @Override
public Purchase confirmPurchase(Long notificationId, String sellerUsername) {
    Optional<Notification> notificationOptional = notificationRepository.findById(notificationId);
    if (notificationOptional.isPresent()) {
        Notification notification = notificationOptional.get();
        Product product = notification.getProduct();
        if (product.getSeller().getUsername().equals(sellerUsername)) {
            product.setAvailableQuantity(product.getAvailableQuantity() - notification.getQuantrequested());
            productRepository.save(product);
            notification.setRead(true);
            Purchase purchase = Purchase.builder()
                .product(product)
                .notification(notification)
                .buyer(notification.getSender())
                .seller(product.getSeller())
                .quantity(notification.getQuantrequested())
                .totalPrice(product.getPrice()*notification.getQuantrequested())
                .build();
            return purchaseRepository.save(purchase);
        }
    }
    return null;
}


}
