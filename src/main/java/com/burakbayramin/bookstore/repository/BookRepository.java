package com.burakbayramin.bookstore.repository;

import com.burakbayramin.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
