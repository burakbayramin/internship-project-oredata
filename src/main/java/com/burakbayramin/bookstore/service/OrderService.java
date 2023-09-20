package com.burakbayramin.bookstore.service;

import com.burakbayramin.bookstore.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(Long userId, OrderDto orderDto);

    List<OrderDto> getAllOrdersFromUser(Long id);

    OrderDto getOrderById(Long userId, Long orderId);
}
