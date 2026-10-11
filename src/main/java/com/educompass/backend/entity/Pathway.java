package com.educompass.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "educational_pathways")
public class Pathway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pathway code is required (e.g. MPC, BIPC, POLYCET)")
    @Column(nullable = false, unique = true)
    private String code;

    @NotBlank(message = "Pathway name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Duration is required")
    @Column(nullable = false)
    private String duration; // e.g. "2 Years (Intermediate)", "3 Years (Diploma)"

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String whoShouldChoose;

    @Column(columnDefinition = "TEXT")
    private String coreSubjects;

    @Column(columnDefinition = "TEXT")
    private String nextSteps; // Exams, courses after this pathway

    @Column(columnDefinition = "TEXT")
    private String leadingCareers;

    @Column(columnDefinition = "TEXT")
    private String prosAndCons;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Pathway() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhoShouldChoose() {
        return whoShouldChoose;
    }

    public void setWhoShouldChoose(String whoShouldChoose) {
        this.whoShouldChoose = whoShouldChoose;
    }

    public String getCoreSubjects() {
        return coreSubjects;
    }

    public void setCoreSubjects(String coreSubjects) {
        this.coreSubjects = coreSubjects;
    }

    public String getNextSteps() {
        return nextSteps;
    }

    public void setNextSteps(String nextSteps) {
        this.nextSteps = nextSteps;
    }

    public String getLeadingCareers() {
        return leadingCareers;
    }

    public void setLeadingCareers(String leadingCareers) {
        this.leadingCareers = leadingCareers;
    }

    public String getProsAndCons() {
        return prosAndCons;
    }

    public void setProsAndCons(String prosAndCons) {
        this.prosAndCons = prosAndCons;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
