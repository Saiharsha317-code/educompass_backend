package com.educompass.backend.service;

import com.educompass.backend.entity.EntranceExam;
import com.educompass.backend.exception.ResourceNotFoundException;
import com.educompass.backend.repository.EntranceExamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EntranceExamService {

    private final EntranceExamRepository examRepository;

    public EntranceExamService(EntranceExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Transactional(readOnly = true)
    public List<EntranceExam> getAllExams(String category, String stage, String query) {
        if (query != null && !query.trim().isEmpty()) {
            return examRepository.searchExams(query.trim());
        }
        if (category != null && !category.trim().isEmpty()) {
            return examRepository.findByCategoryIgnoreCase(category.trim());
        }
        if (stage != null && !stage.trim().isEmpty()) {
            return examRepository.findByApplicableStageContainingIgnoreCase(stage.trim());
        }
        return examRepository.findAll();
    }

    @Transactional(readOnly = true)
    public EntranceExam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrance exam not found with ID: " + id));
    }

    public EntranceExam createExam(EntranceExam exam) {
        exam.setId(null);
        return examRepository.save(exam);
    }

    public EntranceExam updateExam(Long id, EntranceExam updated) {
        EntranceExam existing = getExamById(id);
        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        existing.setTargetField(updated.getTargetField());
        existing.setApplicableStage(updated.getApplicableStage());
        existing.setEligibility(updated.getEligibility());
        existing.setExamPurpose(updated.getExamPurpose());
        existing.setExamPattern(updated.getExamPattern());
        existing.setOfficialLink(updated.getOfficialLink());
        existing.setVerificationStatus(updated.getVerificationStatus());
        existing.setNotes(updated.getNotes());
        return examRepository.save(existing);
    }

    public void deleteExam(Long id) {
        EntranceExam existing = getExamById(id);
        examRepository.delete(existing);
    }
}
