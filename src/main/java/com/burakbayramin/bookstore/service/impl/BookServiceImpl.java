package com.burakbayramin.bookstore.service.impl;

import com.burakbayramin.bookstore.dto.BookDto;
import com.burakbayramin.bookstore.entity.Book;
import com.burakbayramin.bookstore.exception.ResourceNotFoundException;
import com.burakbayramin.bookstore.repository.BookRepository;
import com.burakbayramin.bookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;


    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;

    }

    private BookDto mapToDto(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }

    private Book mapToEntity(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        return book;
    }

    @CachePut(value = "books", key = "#id")
    @Override
    public BookDto addBook(BookDto bookDto) {
        Book book = mapToEntity(bookDto);
        bookRepository.save(book);
        return mapToDto(book);
    }

    @Cacheable(value = "books")
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(book -> mapToDto(book)).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return mapToDto(book);
    }

    @CacheEvict(value = "books", allEntries = true)
    @Override
    public BookDto updateBook(BookDto bookDto, String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        book.setISBN(bookDto.getISBN());
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());
        book.setStockQuantity(book.getStockQuantity());

        Book updatedBook = bookRepository.save(book);
        return mapToDto(updatedBook);
    }

    @CacheEvict(value = "books", allEntries = true)
    @Override
    public void deleteBookById(String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        bookRepository.delete(book);
    }

}
