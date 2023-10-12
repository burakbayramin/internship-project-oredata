package com.burakbayramin.bookstore.repository;

import com.burakbayramin.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long id);

//    @Query("SELECT DISTINCT o FROM Order o " +
//            "JOIN o.books b " +
//            "JOIN o.user u " +
//            "WHERE c.customerName = :customerName " +
//            "AND b.author = :author " +
//            "AND b.price BETWEEN :minPrice AND :maxPrice")
//    List<Order> findOrdersByCustomerNameAndAuthorAndPriceRange(
//            @Param("userName") String name,
//            @Param("author") String author,
//            @Param("minPrice") double minPrice,
//            @Param("maxPrice") double maxPrice
//    );
}
