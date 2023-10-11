package com.burakbayramin.bookstore.service.impl;

import com.burakbayramin.bookstore.dto.OrderDto;
import com.burakbayramin.bookstore.entity.Order;
import com.burakbayramin.bookstore.entity.User;
import com.burakbayramin.bookstore.exception.APIException;
import com.burakbayramin.bookstore.exception.ResourceNotFoundException;
import com.burakbayramin.bookstore.repository.OrderRepository;
import com.burakbayramin.bookstore.repository.UserRepository;
import com.burakbayramin.bookstore.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    private OrderDto mapToDto(Order order) {
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        return orderDto;
    }

    private Order mapToEntity(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        return order;
    }

    @CachePut(value = "orders", key = "#id")
    @Override
    public OrderDto createOrder(Long userId, OrderDto orderDto) {

        Order order = mapToEntity(orderDto);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));

        order.setUser(user);

        Order newOrder = orderRepository.save(order);

        return mapToDto(newOrder);
    }

    @Cacheable(value = "orders")
    @Override
    public List<OrderDto> getAllOrdersFromUser(Long id) {

        List<Order> orders = orderRepository.findByUserId(id);

        return orders.stream().map(order -> mapToDto(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long userId, Long orderId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId.toString()));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "User doesn't have this order");
        }

        return mapToDto(order);
    }
}
