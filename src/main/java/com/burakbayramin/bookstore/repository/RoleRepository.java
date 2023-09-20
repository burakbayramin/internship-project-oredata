package com.burakbayramin.bookstore.repository;

import com.burakbayramin.bookstore.entity.Role;
import com.burakbayramin.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
