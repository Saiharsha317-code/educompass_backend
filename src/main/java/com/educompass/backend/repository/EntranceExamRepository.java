package com.educompass.backend.repository;

import com.educompass.backend.entity.EntranceExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntranceExamRepository extends JpaRepository<EntranceExam, Long> {

    List<EntranceExam> findByCategoryIgnoreCase(String category);

    List<EntranceExam> findByApplicableStageContainingIgnoreCase(String applicableStage);

    @Query("SELECT e FROM EntranceExam e WHERE " +
           "LOWER(e.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(e.category) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(e.targetField) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(e.examPurpose) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<EntranceExam> searchExams(@Param("query") String query);
}
