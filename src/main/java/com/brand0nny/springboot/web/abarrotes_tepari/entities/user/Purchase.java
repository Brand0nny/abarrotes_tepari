package com.brand0nny.springboot.web.abarrotes_tepari.entities.user;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="purchase")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "buyer_id")
private User buyer;
@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "seller_id")
private User seller;
@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "product_id")
private Product product;

@Column
private Double totalPrice;
@Column
private int quantity;
@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id")
    private Notification notification;
}
