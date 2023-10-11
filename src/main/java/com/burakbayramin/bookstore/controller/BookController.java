package com.burakbayramin.bookstore.controller;

import com.burakbayramin.bookstore.dto.BookDto;
import com.burakbayramin.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
            @Valid @RequestBody BookDto bookDto,
            @PathVariable(name = "id") String id
    ) {
        BookDto response = bookService.updateBook(bookDto, id);
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") String id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book deleted");
    }
}
