package com.example.demo.retivio.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.retivio.model.Review;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JsonReviewRepository {
    private List<Review> reviews = new ArrayList<>();

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream is = new ClassPathResource("data/reviews.json").getInputStream();
            reviews = mapper.readValue(is, new TypeReference<List<Review>>() {});
        } catch (Exception e) {}
    }

    public List<Review> findAll() { return reviews; }

    public Review save(Review review) {
        reviews.add(review);
        return review;
    }
}