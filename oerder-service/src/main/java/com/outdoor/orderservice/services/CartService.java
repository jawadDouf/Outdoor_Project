package com.outdoor.orderservice.services;


import com.outdoor.orderservice.dto.CartDto;
import com.outdoor.orderservice.entities.Cart;
import com.outdoor.orderservice.enums.CartStatus;
import com.outdoor.orderservice.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {


    @Autowired
    final private CartRepository cartRepository;


    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    public List<CartDto> getAll() {
        List<Cart> carts = cartRepository.findAll();
        return convertToDtos(carts);
    }

    public CartDto addCart(CartDto cartDto) {
        cartRepository.save(convertToEntity(cartDto));
        return cartDto;
    }




    public CartDto updateCart(CartDto cartDto) {
        cartRepository.save(convertToEntity(cartDto));
        return cartDto;
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }




    private Cart convertToEntity(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setProductId(cartDto.getProductId());
        cart.setUserId(cartDto.getUserId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setOrderId(cartDto.getOrderId());
        return cart;
    }

    private List<CartDto> convertToDtos(List<Cart> carts) {
        List<CartDto> cartDtos = new ArrayList<>();
        carts.forEach(cart -> {
            CartDto cartDto = new CartDto();
            cartDto.setId(cart.getId());
            cartDto.setProductId(cart.getProductId());
            cartDto.setUserId(cart.getUserId());
            cartDto.setQuantity(cart.getQuantity());
            cartDto.setOrderId(cart.getOrderId());
            cartDtos.add(cartDto);
        });
        return cartDtos;
    }

    public void updateCartStatus(Long orderId, Long userId) {
        List<Cart> carts = cartRepository.findAllByUserId(userId);
        carts.forEach(cart -> {
            cart.setOrderId(orderId);
            cart.setStatus(CartStatus.INACTIVE);
            cartRepository.save(cart);
        });
    }
}
