package com.educompass.backend.controller;

import com.educompass.backend.entity.EntranceExam;
import com.educompass.backend.service.EntranceExamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class EntranceExamController {

    private final EntranceExamService examService;

    public EntranceExamController(EntranceExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public ResponseEntity<List<EntranceExam>> getAllExams(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String stage,
            @RequestParam(required = false) String search) {
        List<EntranceExam> exams = examService.getAllExams(category, stage, search);
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntranceExam> getExamById(@PathVariable Long id) {
        EntranceExam exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }

    @PostMapping
    public ResponseEntity<EntranceExam> createExam(@Valid @RequestBody EntranceExam exam) {
        EntranceExam created = examService.createExam(exam);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntranceExam> updateExam(@PathVariable Long id, @Valid @RequestBody EntranceExam exam) {
        EntranceExam updated = examService.updateExam(id, exam);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
