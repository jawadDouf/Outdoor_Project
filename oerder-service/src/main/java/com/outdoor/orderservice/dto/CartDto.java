package com.outdoor.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CartDto {
    private Long id;
    private Long productId;
    private Long userId;
    private Integer quantity;
    private Long orderId;
}
