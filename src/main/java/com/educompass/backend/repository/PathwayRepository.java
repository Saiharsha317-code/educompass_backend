package com.educompass.backend.repository;

import com.educompass.backend.entity.Pathway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PathwayRepository extends JpaRepository<Pathway, Long> {
    Optional<Pathway> findByCodeIgnoreCase(String code);
}
