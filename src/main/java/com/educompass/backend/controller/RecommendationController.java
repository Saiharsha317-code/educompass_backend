package com.educompass.backend.controller;

import com.educompass.backend.dto.RecommendationRequest;
import com.educompass.backend.dto.RecommendationResponse;
import com.educompass.backend.service.RecommendationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping
    public ResponseEntity<RecommendationResponse> getRecommendations(
            @Valid @RequestBody RecommendationRequest request) {
        RecommendationResponse response = recommendationService.generateRecommendations(request);
        return ResponseEntity.ok(response);
    }
}
