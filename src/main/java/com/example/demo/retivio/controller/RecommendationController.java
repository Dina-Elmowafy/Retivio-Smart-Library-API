package com.example.demo.retivio.controller;

import com.example.demo.retivio.service.AiRecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final AiRecommendationService aiService;

    public RecommendationController(AiRecommendationService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getRecommendations(
            @PathVariable String userId,
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) String localIsbns) { // 👈 ضفنا السطر ده هنا

        // 👈 وضفناه هنا كمان عشان يتبعت للسيرفيس
        Object recommendations = aiService.getRecommendationsForUser(userId, searchText, localIsbns);
        return ResponseEntity.ok(recommendations);
    }
}