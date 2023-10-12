package com.burakbayramin.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ISBN;

    private String title;

    private String author;

    private double price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private Set<Order> orders;

}
