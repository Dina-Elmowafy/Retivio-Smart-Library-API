package com.example.demo.retivio.controller;

import com.example.demo.retivio.model.Review;
import com.example.demo.retivio.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST API controller
@RequestMapping("/api/reviews") // Base URL path for all endpoints in this controller
public class ReviewController {

    private final ReviewService reviewService; // Service layer dependency for review business logic

    // Constructor-based dependency injection (Spring will auto-wire the service)
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Retrieves all reviews from the system.
     *
     * @return ResponseEntity containing a list of all reviews with HTTP status 200 (OK)
     *
     * Example response: HTTP 200 OK with body
     * [{"id":1,"userId":"user123","isbn":"978-3-16-148410-0","rating":5,"comment":"Great book!"}, ...]
     */
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    /**
     * Adds a new review to the system.
     *
     * @param review - The review object to be added (passed in the request body)
     * @return ResponseEntity containing the created review with HTTP status 201 (Created)
     *         if the review is valid, or HTTP status 400 (Bad Request) if required fields are missing
     *
     * Required fields: userId, isbn, rating (must all be non-null)
     *
     * Example request body: {"userId":"user123","isbn":"978-3-16-148410-0","rating":5,"comment":"Excellent read!"}
     * Example success response: HTTP 201 Created with body including generated id
     * Example error response: HTTP 400 Bad Request (empty body)
     */
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        // Validate that all required fields are present
        if (review.getUserId() == null || review.getIsbn() == null || review.getRating() == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if validation fails
        }
        // Delegate to service layer for persistence and return 201 Created status
        return ResponseEntity.status(201).body(reviewService.addReview(review));
    }
}