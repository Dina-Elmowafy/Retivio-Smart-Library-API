package com.example.demo.retivio.controller;

import com.example.demo.retivio.model.Book;
import com.example.demo.retivio.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST API controller
@RequestMapping("/api/books") // Base URL path for all endpoints in this controller
public class BookController {

    private final BookService bookService; // Service layer dependency for business logic

    // Constructor-based dependency injection
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Retrieves all books from the system.
     * @return ResponseEntity containing a list of all books with HTTP status 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    /**
     * Retrieves a specific book by its ISBN.
     * @param isbn - The unique ISBN identifier of the book (passed as a path variable)
     * @return ResponseEntity containing the found book with HTTP status 200 (OK),
     *         or HTTP status 404 (Not Found) if no book exists with the given ISBN
     */
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        try {
            return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Return 404 if book not found
        }
    }
}