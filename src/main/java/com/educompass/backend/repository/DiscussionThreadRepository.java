package com.educompass.backend.repository;

import com.educompass.backend.entity.DiscussionThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionThreadRepository extends JpaRepository<DiscussionThread, Long> {
    List<DiscussionThread> findAllByOrderByCreatedAtDesc();
    List<DiscussionThread> findByCategoryIgnoreCaseOrderByCreatedAtDesc(String category);
}
