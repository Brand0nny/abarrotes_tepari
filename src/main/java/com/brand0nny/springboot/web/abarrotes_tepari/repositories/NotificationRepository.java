package com.brand0nny.springboot.web.abarrotes_tepari.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    
}
