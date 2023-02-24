package com.outdoor.orderservice.services;


import com.outdoor.orderservice.dto.OrderDto;
import com.outdoor.orderservice.entities.Order;
import com.outdoor.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    final private OrderRepository orderRepository;

    @Autowired
    final private CartService cartService;

    public OrderService(OrderRepository orderRepository, CartService cartService){
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    public List<OrderDto> getAll() {
        List<Order> orders = this.orderRepository.findAll();
        return convertToDtos(orders);
    }

    private List<OrderDto> convertToDtos(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order ->{
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setOrderCode(order.getOrderCode());
            orderDtos.add(orderDto);
        });
        return orderDtos;
    }

    public OrderDto addOrder(OrderDto orderDto, Long userId) {
        Order order = new Order();
        order.setOrderCode(orderDto.getOrderCode());
        Order savedOrder = this.orderRepository.save(order);
        Long orderId = savedOrder.getId();
        cartService.updateCartStatus(orderId, userId);
        return orderDto;
    }

    public OrderDto updateOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setOrderCode(orderDto.getOrderCode());
        orderRepository.save(order);
        return convertToDto(order);
    }

    private OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderCode(order.getOrderCode());
        return orderDto;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDto getOrderById(Long id) {
        Order order = this.orderRepository.findById(id).get();
        return convertToDto(order);
    }
}
