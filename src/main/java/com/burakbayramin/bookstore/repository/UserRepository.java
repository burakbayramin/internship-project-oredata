package com.burakbayramin.bookstore.repository;

import com.burakbayramin.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

    Optional<User> findByNameOrEmail(String name, String email);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

//    @Query("SELECT u.name, o.id, o.totalPrice, o.orderDate, b.title, b.author, b.price " +
//            "FROM User u " +
//            "INNER JOIN u.orders o " +
//            "LEFT JOIN o.books b " +
//            "WHERE u.id = :userId")
//    List<Object[]> getUserOrdersWithBooks(@Param("userId") Long userId);
}
