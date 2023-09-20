package com.burakbayramin.bookstore.service;

import com.burakbayramin.bookstore.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto addBook(BookDto bookDto);

    List<BookDto> getAllBooks();

    BookDto getBookById(String id);

    BookDto updateBook(BookDto bookDto, String id);

    void deleteBookById(String id);
}
