package com.educompass.backend.controller;

import com.educompass.backend.dto.ThreadReplyRequest;
import com.educompass.backend.entity.DiscussionThread;
import com.educompass.backend.service.DiscussionThreadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/threads")
public class DiscussionThreadController {

    private final DiscussionThreadService threadService;

    public DiscussionThreadController(DiscussionThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping
    public ResponseEntity<List<DiscussionThread>> getAllThreads(
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(threadService.getAllThreads(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscussionThread> getThreadById(@PathVariable Long id) {
        return ResponseEntity.ok(threadService.getThreadById(id));
    }

    @PostMapping
    public ResponseEntity<DiscussionThread> createThread(@Valid @RequestBody DiscussionThread thread) {
        DiscussionThread created = threadService.createThread(thread);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscussionThread> updateThread(@PathVariable Long id, @Valid @RequestBody DiscussionThread thread) {
        DiscussionThread updated = threadService.updateThread(id, thread);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThread(@PathVariable Long id) {
        threadService.deleteThread(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/replies")
    public ResponseEntity<DiscussionThread> addReply(
            @PathVariable Long id,
            @Valid @RequestBody ThreadReplyRequest replyRequest) {
        DiscussionThread updated = threadService.addReply(id, replyRequest.getAuthor(), replyRequest.getContent());
        return ResponseEntity.ok(updated);
    }
}
