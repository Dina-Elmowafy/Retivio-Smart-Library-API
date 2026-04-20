package com.example.demo.retivio.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.retivio.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // Marks this class as a Spring Data Access Layer component (DAO)
public class JsonBookRepository {

    private List<Book> books = new ArrayList<>(); // In-memory list to store books loaded from JSON

    /**
     * This method runs automatically after the bean is initialized (when the server starts).
     * It loads book data from a JSON file into memory.
     *
     * The @PostConstruct annotation ensures this method is called once during application startup,
     * right after dependency injection is complete.
     */
    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper(); // Jackson's JSON parser
        try {
            // Read the books.json file from the classpath (src/main/resources/data/books.json)
            InputStream is = new ClassPathResource("data/books.json").getInputStream();

            // Deserialize JSON array into a List<Book> object
            books = mapper.readValue(is, new TypeReference<List<Book>>() {});

            System.out.println("✅ Books loaded successfully!");
        } catch (Exception e) {
            System.out.println("❌ Could not load books.json: " + e.getMessage());
        }
    }

    /**
     * Retrieves all books from the in-memory list.
     *
     * @return List containing all Book objects
     */
    public List<Book> findAll() {
        return books;
    }

    /**
     * Finds a book by its ISBN (International Standard Book Number).
     *
     * @param isbn - The unique ISBN of the book to search for
     * @return Optional containing the Book if found, or empty Optional if not found
     */
    public Optional<Book> findByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst(); // Returns first matching book (ISBN should be unique)
    }

    /**
     * Saves a new book to the in-memory list.
     *
     * @param book - The Book object to add
     * @return The saved Book object (with any generated data, if applicable)
     *
     * Note: Currently this only adds to memory. To make changes persistent,
     * you would need to add code here to write back to the JSON file.
     */
    public Book save(Book book) {
        books.add(book);
        // TODO: Add code here to write to books.json if you want to persist changes permanently
        return book;
    }
}