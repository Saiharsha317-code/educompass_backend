package com.educompass.backend.service;

import com.educompass.backend.entity.DiscussionThread;
import com.educompass.backend.entity.ThreadReply;
import com.educompass.backend.exception.ResourceNotFoundException;
import com.educompass.backend.repository.DiscussionThreadRepository;
import com.educompass.backend.repository.ThreadReplyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiscussionThreadService {

    private final DiscussionThreadRepository threadRepository;
    private final ThreadReplyRepository replyRepository;

    public DiscussionThreadService(DiscussionThreadRepository threadRepository, ThreadReplyRepository replyRepository) {
        this.threadRepository = threadRepository;
        this.replyRepository = replyRepository;
    }

    @Transactional(readOnly = true)
    public List<DiscussionThread> getAllThreads(String category) {
        if (category != null && !category.trim().isEmpty()) {
            return threadRepository.findByCategoryIgnoreCaseOrderByCreatedAtDesc(category.trim());
        }
        return threadRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public DiscussionThread getThreadById(Long id) {
        return threadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discussion thread not found with ID: " + id));
    }

    public DiscussionThread createThread(DiscussionThread thread) {
        thread.setId(null);
        thread.setReplyCount(0);
        return threadRepository.save(thread);
    }

    public DiscussionThread updateThread(Long id, DiscussionThread updated) {
        DiscussionThread existing = getThreadById(id);
        existing.setTitle(updated.getTitle());
        existing.setContent(updated.getContent());
        existing.setCategory(updated.getCategory());
        return threadRepository.save(existing);
    }

    public void deleteThread(Long id) {
        DiscussionThread existing = getThreadById(id);
        threadRepository.delete(existing);
    }

    public DiscussionThread addReply(Long threadId, String author, String content) {
        DiscussionThread thread = getThreadById(threadId);
        ThreadReply reply = new ThreadReply(author, content);
        thread.addReply(reply);
        return threadRepository.save(thread);
    }
}
