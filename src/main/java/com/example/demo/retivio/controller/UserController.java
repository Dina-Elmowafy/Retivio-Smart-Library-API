package com.example.demo.retivio.controller;

import com.example.demo.retivio.model.User;
import com.example.demo.retivio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST API controller
@RequestMapping("/api/users") // Base URL path for all user-related endpoints
public class UserController {

    private final UserService userService; // Service layer dependency for user business logic

    // Constructor-based dependency injection (Spring will auto-wire the service)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves all users from the system.
     *
     * @return ResponseEntity containing a list of all users with HTTP status 200 (OK)
     *
     * Example response: HTTP 200 OK with body
     * [{"id":"123","name":"John Doe","email":"john@example.com"}, ...]
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Retrieves a specific user by their unique ID.
     *
     * @param id - The unique identifier of the user (passed as a path variable)
     * @return ResponseEntity containing the found user with HTTP status 200 (OK),
     *         or HTTP status 404 (Not Found) if no user exists with the given ID
     *
     * Example request: GET /api/users/123
     * Example success response: HTTP 200 OK with body {"id":"123","name":"John Doe","email":"john@example.com"}
     * Example error response: HTTP 404 Not Found (empty body)
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Return 404 if user not found
        }
    }

    /**
     * Creates a new user in the system.
     *
     * @param user - The user object to be created (passed in the request body)
     * @return ResponseEntity containing the created user with HTTP status 201 (Created)
     *         if the user data is valid, or HTTP status 400 (Bad Request) if required fields are missing
     *
     * Required fields: name, email (must both be non-null)
     *
     * Example request body: {"name":"John Doe","email":"john@example.com"}
     * Example success response: HTTP 201 Created with body including generated id
     *         {"id":"123","name":"John Doe","email":"john@example.com"}
     * Example error response: HTTP 400 Bad Request (empty body)
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Validate that required fields are present
        if (user.getName() == null || user.getEmail() == null) {
            return ResponseEntity.badRequest().build(); // Return 400 if validation fails
        }
        // Delegate to service layer for persistence and return 201 Created status
        return ResponseEntity.status(201).body(userService.createUser(user));
    }
}