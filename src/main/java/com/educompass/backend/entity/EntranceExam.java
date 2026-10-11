package com.educompass.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "entrance_exams")
public class EntranceExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Exam name is required")
    @Column(nullable = false)
    private String name;

    /**
     * Category distinguishes between ADMISSION vs EMPLOYMENT_RECRUITMENT
     */
    @NotBlank(message = "Category is required (e.g. ADMISSION or EMPLOYMENT_RECRUITMENT)")
    @Column(nullable = false)
    private String category;

    @NotBlank(message = "Target field is required")
    @Column(nullable = false)
    private String targetField;

    @NotBlank(message = "Applicable stage is required")
    @Column(nullable = false)
    private String applicableStage; // e.g. "After Class 10", "After Intermediate (10+2)", "After Degree"

    @Column(columnDefinition = "TEXT")
    private String eligibility;

    @Column(columnDefinition = "TEXT")
    private String examPurpose;

    @Column(columnDefinition = "TEXT")
    private String examPattern;

    @Column(nullable = false)
    private String officialLink;

    @Column(columnDefinition = "TEXT")
    private String verificationStatus; // e.g. "Verified from TSCHE official portal; dates subject to annual notification"

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public EntranceExam() {}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }

    public String getApplicableStage() {
        return applicableStage;
    }

    public void setApplicableStage(String applicableStage) {
        this.applicableStage = applicableStage;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getExamPurpose() {
        return examPurpose;
    }

    public void setExamPurpose(String examPurpose) {
        this.examPurpose = examPurpose;
    }

    public String getExamPattern() {
        return examPattern;
    }

    public void setExamPattern(String examPattern) {
        this.examPattern = examPattern;
    }

    public String getOfficialLink() {
        return officialLink;
    }

    public void setOfficialLink(String officialLink) {
        this.officialLink = officialLink;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
