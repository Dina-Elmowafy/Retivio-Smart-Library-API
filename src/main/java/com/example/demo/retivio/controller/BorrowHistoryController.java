package com.example.demo.retivio.controller;

import com.example.demo.retivio.model.BorrowRecord;
import com.example.demo.retivio.service.BorrowHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST API controller
@RequestMapping("/api/borrow_history") // Base URL path for all endpoints in this controller
public class BorrowHistoryController {

    private final BorrowHistoryService historyService; // Service layer dependency for borrow history business logic

    // Constructor-based dependency injection (Spring will auto-wire the service)
    public BorrowHistoryController(BorrowHistoryService historyService) {
        this.historyService = historyService;
    }

    /**
     * Retrieves all borrow records from the system.
     *
     * @return ResponseEntity containing a list of all borrow records with HTTP status 200 (OK)
     *
     * Example response: HTTP 200 OK with body [{"id":1,"userId":"user123","isbn":"978-3-16-148410-0"}, ...]
     */
    @GetMapping
    public ResponseEntity<List<BorrowRecord>> getAllHistory() {
        return ResponseEntity.ok(historyService.getAllHistory());
    }

    /**
     * Adds a new borrow record to the system.
     *
     * @param record - The borrow record to be added (passed in the request body)
     * @return ResponseEntity containing the created borrow record with HTTP status 201 (Created)
     *         if the record is valid, or HTTP status 400 (Bad Request) if userId or ISBN is missing
     *
     * Example request body: {"userId":"user123","isbn":"978-3-16-148410-0"}
     * Example success response: HTTP 201 Created with body including generated id
     * Example error response: HTTP 400 Bad Request (empty body)
     */
    @PostMapping
    public ResponseEntity<BorrowRecord> addRecord(@RequestBody BorrowRecord record) {
        // Validate that required fields are present (userId and ISBN cannot be null)
        if (record.getUserId() == null || record.getIsbn() == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if validation fails
        }
        // Delegate to service layer for persistence and return 201 Created status
        return ResponseEntity.status(201).body(historyService.addRecord(record));
    }
}