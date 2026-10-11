package com.educompass.backend.service;

import com.educompass.backend.entity.Career;
import com.educompass.backend.exception.ResourceNotFoundException;
import com.educompass.backend.repository.CareerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CareerService {

    private final CareerRepository careerRepository;

    public CareerService(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }

    @Transactional(readOnly = true)
    public List<Career> getAllCareers(String category, String query) {
        if (query != null && !query.trim().isEmpty()) {
            return careerRepository.searchCareers(query.trim());
        }
        if (category != null && !category.trim().isEmpty()) {
            return careerRepository.findByCategoryIgnoreCase(category.trim());
        }
        return careerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Career getCareerById(Long id) {
        return careerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Career path not found with ID: " + id));
    }

    public Career createCareer(Career career) {
        career.setId(null);
        return careerRepository.save(career);
    }

    public Career updateCareer(Long id, Career updatedCareer) {
        Career existing = getCareerById(id);
        existing.setTitle(updatedCareer.getTitle());
        existing.setCategory(updatedCareer.getCategory());
        existing.setStreamRequired(updatedCareer.getStreamRequired());
        existing.setDescription(updatedCareer.getDescription());
        existing.setWhoShouldConsider(updatedCareer.getWhoShouldConsider());
        existing.setKeySkills(updatedCareer.getKeySkills());
        existing.setEducationRoute(updatedCareer.getEducationRoute());
        existing.setEligibility(updatedCareer.getEligibility());
        existing.setEntranceExams(updatedCareer.getEntranceExams());
        existing.setPossibleCourses(updatedCareer.getPossibleCourses());
        existing.setCareerOpportunities(updatedCareer.getCareerOpportunities());
        existing.setGovSeatAndScholarshipInfo(updatedCareer.getGovSeatAndScholarshipInfo());
        existing.setFurtherStudyOptions(updatedCareer.getFurtherStudyOptions());
        return careerRepository.save(existing);
    }

    public void deleteCareer(Long id) {
        Career existing = getCareerById(id);
        careerRepository.delete(existing);
    }
}
