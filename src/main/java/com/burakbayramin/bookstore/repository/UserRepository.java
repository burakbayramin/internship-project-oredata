package com.burakbayramin.bookstore.repository;

import com.burakbayramin.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

    Optional<User> findByNameOrEmail(String name, String email);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);
}
