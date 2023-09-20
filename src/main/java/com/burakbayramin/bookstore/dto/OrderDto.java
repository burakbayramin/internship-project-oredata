package com.burakbayramin.bookstore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private Long id;

    @NotNull
    private double totalPrice;

    private LocalDateTime orderDate;

    private List<BookDto> books;

    private UserDto user;
}
