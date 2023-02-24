package com.outdoor.orderservice.entities;


import com.outdoor.orderservice.enums.CartStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long userId;
    private Integer quantity;
    private Long orderId;
    @Enumerated(EnumType.STRING)
    private CartStatus status = CartStatus.ACTIVE;

}
