package com.brand0nny.springboot.web.abarrotes_tepari.dto;

import java.util.Date;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    long id;
    String name;
    String description;
    double price;
    String date;
    String imageUrl;
    int quantAvailable;
    String seller;
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.date = product.getDate();
        this.imageUrl = product.getImageUrl();
        this.seller = product.getSeller().getUsername();
    }


    
}
