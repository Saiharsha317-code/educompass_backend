package com.educompass.backend.service;

import com.educompass.backend.entity.Pathway;
import com.educompass.backend.exception.ResourceNotFoundException;
import com.educompass.backend.repository.PathwayRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PathwayService {

    private final PathwayRepository pathwayRepository;

    public PathwayService(PathwayRepository pathwayRepository) {
        this.pathwayRepository = pathwayRepository;
    }

    @Transactional(readOnly = true)
    public List<Pathway> getAllPathways() {
        return pathwayRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pathway getPathwayById(Long id) {
        return pathwayRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pathway not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Pathway getPathwayByCode(String code) {
        return pathwayRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new ResourceNotFoundException("Pathway not found with code: " + code));
    }

    public Pathway createPathway(Pathway pathway) {
        pathway.setId(null);
        return pathwayRepository.save(pathway);
    }

    public Pathway updatePathway(Long id, Pathway updated) {
        Pathway existing = getPathwayById(id);
        existing.setName(updated.getName());
        existing.setCode(updated.getCode());
        existing.setDuration(updated.getDuration());
        existing.setDescription(updated.getDescription());
        existing.setWhoShouldChoose(updated.getWhoShouldChoose());
        existing.setCoreSubjects(updated.getCoreSubjects());
        existing.setNextSteps(updated.getNextSteps());
        existing.setLeadingCareers(updated.getLeadingCareers());
        existing.setProsAndCons(updated.getProsAndCons());
        return pathwayRepository.save(existing);
    }

    public void deletePathway(Long id) {
        Pathway existing = getPathwayById(id);
        pathwayRepository.delete(existing);
    }
}
