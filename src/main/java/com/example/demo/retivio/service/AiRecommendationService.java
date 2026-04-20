package com.example.demo.retivio.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service // Marks this class as a Spring Service Layer component (business logic)
public class AiRecommendationService {

    private final RestTemplate restTemplate; // HTTP client for making REST API calls
    private final String PYTHON_AI_URL = "http://localhost:8001"; // Base URL of the Python AI service

    // Constructor-based dependency injection for RestTemplate
    public AiRecommendationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches AI-powered book recommendations for a user by calling an external Python service.
     *
     * @param userId - Unique identifier of the user requesting recommendations
     * @param searchText - Optional search text/keywords to filter recommendations (can be null/empty)
     * @param localIsbns - Optional comma-separated list of ISBNs that are locally available (can be null/empty)
     * @return Object containing AI recommendations from the Python service (typically JSON response)
     * @throws RuntimeException if the AI service call fails (connection error, timeout, etc.)
     *
     * Example URL: http://localhost:8001/recommendations/user123?searchText=harry+potter&localIsbns=978-3-16-148410-0,978-0-43-902348-1
     */
    public Object getRecommendationsForUser(String userId, String searchText, String localIsbns) {
        // Build the URL for the Python AI service endpoint
        StringBuilder url = new StringBuilder(PYTHON_AI_URL + "/recommendations/" + userId + "?");

        // Append searchText query parameter if provided (for keyword-based filtering)
        if (searchText != null && !searchText.isEmpty()) {
            url.append("searchText=").append(searchText).append("&");
        }

        // Append localIsbns query parameter if provided - sends available book ISBNs to Python service
        // The Python service can use these to filter recommendations based on local inventory
        if (localIsbns != null && !localIsbns.isEmpty()) {
            url.append("localIsbns=").append(localIsbns);
        }

        try {
            // Make synchronous GET request to the Python AI service and deserialize response to Object
            return restTemplate.getForObject(url.toString(), Object.class);
        } catch (Exception e) {
            // Wrap any exceptions (connection refused, timeout, 4xx/5xx errors) in a runtime exception
            throw new RuntimeException("AI Service Error: " + e.getMessage());
        }
    }
}