package com.outdoor.orderservice.controllers;


import com.outdoor.orderservice.dto.CartDto;
import com.outdoor.orderservice.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    final private CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartDto>> getAll(){
        return new ResponseEntity<>(this.cartService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CartDto> addCart(@RequestBody CartDto cartDto){
        return new ResponseEntity<>(this.cartService.addCart(cartDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CartDto> updateCart(@RequestBody CartDto cartDto){
        return new ResponseEntity<>(this.cartService.updateCart(cartDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id){
        this.cartService.deleteCart(id);
        return new ResponseEntity<>("Cart deleted", HttpStatus.OK);
    }
}
