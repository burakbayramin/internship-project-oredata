package com.burakbayramin.bookstore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {

    private String ISBN;

    @NotEmpty
    @Size(min = 2, message = "Book title should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 2, message = "Book author should have at least 2 characters")
    private String author;

    @NotNull
    private double price;

    @NotNull
    private int stockQuantity;

    private List<OrderDto> orders;
}
