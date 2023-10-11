package com.burakbayramin.bookstore.controller;

import com.burakbayramin.bookstore.dto.OrderDto;
import com.burakbayramin.bookstore.service.OrderService;
import com.burakbayramin.bookstore.service.impl.CreateOrderProducer;
import org.apache.kafka.common.errors.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController("/api/v1")
public class OrderController {

    private final OrderService orderService;
    private final CreateOrderProducer createOrderProducer;

    public OrderController(OrderService orderService, CreateOrderProducer createOrderProducer) {
        this.orderService = orderService;
        this.createOrderProducer = createOrderProducer;
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

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto order) throws ApiException, InterruptedException, ExecutionException {
        createOrderProducer.sendCreateOrderEvent(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
