package com.example.demo.retivio.service;


import com.example.demo.retivio.model.Review;
import com.example.demo.retivio.repository.JsonReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final JsonReviewRepository reviewRepository;

    public ReviewService(JsonReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review addReview(Review review) {
        review.setId("r-" + System.currentTimeMillis());
        review.setTimestamp(System.currentTimeMillis());
        return reviewRepository.save(review);
    }
}