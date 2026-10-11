package com.educompass.backend.controller;

import com.educompass.backend.entity.Pathway;
import com.educompass.backend.service.PathwayService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pathways")
public class PathwayController {

    private final PathwayService pathwayService;

    public PathwayController(PathwayService pathwayService) {
        this.pathwayService = pathwayService;
    }

    @GetMapping
    public ResponseEntity<List<Pathway>> getAllPathways() {
        return ResponseEntity.ok(pathwayService.getAllPathways());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pathway> getPathwayById(@PathVariable Long id) {
        return ResponseEntity.ok(pathwayService.getPathwayById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Pathway> getPathwayByCode(@PathVariable String code) {
        return ResponseEntity.ok(pathwayService.getPathwayByCode(code));
    }

    @PostMapping
    public ResponseEntity<Pathway> createPathway(@Valid @RequestBody Pathway pathway) {
        Pathway created = pathwayService.createPathway(pathway);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pathway> updatePathway(@PathVariable Long id, @Valid @RequestBody Pathway pathway) {
        Pathway updated = pathwayService.updatePathway(id, pathway);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePathway(@PathVariable Long id) {
        pathwayService.deletePathway(id);
        return ResponseEntity.noContent().build();
    }
}
