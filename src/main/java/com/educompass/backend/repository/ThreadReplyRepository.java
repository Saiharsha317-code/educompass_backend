package com.educompass.backend.repository;

import com.educompass.backend.entity.ThreadReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadReplyRepository extends JpaRepository<ThreadReply, Long> {
    List<ThreadReply> findByThreadIdOrderByCreatedAtAsc(Long threadId);
}
