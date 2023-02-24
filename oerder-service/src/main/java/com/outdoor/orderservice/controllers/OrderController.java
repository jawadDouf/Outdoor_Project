package com.outdoor.orderservice.controllers;


import com.outdoor.orderservice.dto.OrderDto;
import com.outdoor.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    @Autowired
    final private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAll(){
        return new ResponseEntity<>(this.orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id){
        return new ResponseEntity<>(this.orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto, @PathVariable Long userId){
        return new ResponseEntity<>(this.orderService.addOrder(orderDto, userId), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(this.orderService.updateOrder(orderDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        this.orderService.deleteOrder(id);
        return new ResponseEntity<>("Order deleted", HttpStatus.OK);
    }
}
