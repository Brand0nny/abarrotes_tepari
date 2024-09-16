package com.brand0nny.springboot.web.abarrotes_tepari.services.implementation;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Notification;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.NotificationRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.ProductRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.PurchaseRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.UserRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.services.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Notification sendPurchaseRequest(String username, Long productId, int quantity) {
        Optional<User> buyerOptional = userRepository.findByUsername(username);
        if (buyerOptional.isPresent()) {
            User buyer = buyerOptional.get();
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                User seller = product.getSeller();
                Notification notification = Notification.builder()
                    .message(String.format("%s wants to buy %d of your product %s.", buyer.getUsername(), quantity, product.getName()))
                    .receiver(seller)
                    .sender(buyer)
                    .sentAt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                    .quantrequested(quantity)
                    .product(product)
                    .build();
                return notificationRepository.save(notification);
            }
        }
        throw new RuntimeException("user not found");
    }
    

}