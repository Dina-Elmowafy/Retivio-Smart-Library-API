package com.example.demo.retivio.service;


import com.example.demo.retivio.model.Book;
import com.example.demo.retivio.repository.JsonBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final JsonBookRepository bookRepository;

    public BookService(JsonBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));
    }
}
