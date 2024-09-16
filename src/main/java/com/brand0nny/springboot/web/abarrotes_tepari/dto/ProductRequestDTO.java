package com.brand0nny.springboot.web.abarrotes_tepari.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private int quantity;

}
