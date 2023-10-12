package com.burakbayramin.bookstore.repository;

import com.burakbayramin.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

//    @Query("SELECT o.orderId, b.title AS bookTitle, b.author AS bookAuthor, o.orderDate, o.quantity " +
//            "FROM Order o " +
//            "JOIN Book b ON o.book.id = b.id " +
//            "WHERE b.stockQuantity < :stockQuantity")
//    List<Book> findOrdersWithLowStock(@Param("stockQuantity") int stockQuantity);

}
