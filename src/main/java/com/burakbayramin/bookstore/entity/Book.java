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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Book book = (Book) o;
//
//        if (Double.compare(price, book.price) != 0) return false;
//        if (stockQuantity != book.stockQuantity) return false;
//        if (!Objects.equals(ISBN, book.ISBN)) return false;
//        if (!Objects.equals(title, book.title)) return false;
//        return Objects.equals(author, book.author);
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        result = ISBN != null ? ISBN.hashCode() : 0;
//        result = 31 * result + (title != null ? title.hashCode() : 0);
//        result = 31 * result + (author != null ? author.hashCode() : 0);
//        temp = Double.doubleToLongBits(price);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        result = 31 * result + stockQuantity;
//        return result;
//    }
}
