package com.burakbayramin.bookstore.controller;

import com.burakbayramin.bookstore.dto.OrderDto;
import com.burakbayramin.bookstore.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/users/{id}/orders")
    public ResponseEntity<OrderDto> createOrder(
            @PathVariable(value = "id") Long id,
            @RequestBody OrderDto orderDto) {

        return new ResponseEntity<>(orderService.createOrder(id, orderDto), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}/orders")
    public List<OrderDto> getAllOrdersFromUser(@PathVariable(value = "id") Long id) {
        return orderService.getAllOrdersFromUser(id);
    }

    @GetMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "orderId") Long orderId
    ) {
        OrderDto orderDto = orderService.getOrderById(userId, orderId);
        return ResponseEntity.ok(orderDto);
    }
}
