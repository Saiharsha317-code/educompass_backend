package com.educompass.backend.controller;

import com.educompass.backend.entity.Career;
import com.educompass.backend.service.CareerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/careers")
public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @GetMapping
    public ResponseEntity<List<Career>> getAllCareers(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search) {
        List<Career> careers = careerService.getAllCareers(category, search);
        return ResponseEntity.ok(careers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Career> getCareerById(@PathVariable Long id) {
        Career career = careerService.getCareerById(id);
        return ResponseEntity.ok(career);
    }

    @PostMapping
    public ResponseEntity<Career> createCareer(@Valid @RequestBody Career career) {
        Career created = careerService.createCareer(career);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Career> updateCareer(@PathVariable Long id, @Valid @RequestBody Career career) {
        Career updated = careerService.updateCareer(id, career);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCareer(@PathVariable Long id) {
        careerService.deleteCareer(id);
        return ResponseEntity.noContent().build();
    }
}
