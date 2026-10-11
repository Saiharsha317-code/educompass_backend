package com.educompass.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "careers")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    private String category;

    @NotBlank(message = "Required stream is required")
    @Column(nullable = false)
    private String streamRequired;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String whoShouldConsider;

    @Column(columnDefinition = "TEXT")
    private String keySkills;

    @Column(columnDefinition = "TEXT")
    private String educationRoute;

    @Column(columnDefinition = "TEXT")
    private String eligibility;

    @Column(columnDefinition = "TEXT")
    private String entranceExams;

    @Column(columnDefinition = "TEXT")
    private String possibleCourses;

    @Column(columnDefinition = "TEXT")
    private String careerOpportunities;

    @Column(columnDefinition = "TEXT")
    private String govSeatAndScholarshipInfo;

    @Column(columnDefinition = "TEXT")
    private String furtherStudyOptions;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Career() {}

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStreamRequired() {
        return streamRequired;
    }

    public void setStreamRequired(String streamRequired) {
        this.streamRequired = streamRequired;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhoShouldConsider() {
        return whoShouldConsider;
    }

    public void setWhoShouldConsider(String whoShouldConsider) {
        this.whoShouldConsider = whoShouldConsider;
    }

    public String getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(String keySkills) {
        this.keySkills = keySkills;
    }

    public String getEducationRoute() {
        return educationRoute;
    }

    public void setEducationRoute(String educationRoute) {
        this.educationRoute = educationRoute;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getEntranceExams() {
        return entranceExams;
    }

    public void setEntranceExams(String entranceExams) {
        this.entranceExams = entranceExams;
    }

    public String getPossibleCourses() {
        return possibleCourses;
    }

    public void setPossibleCourses(String possibleCourses) {
        this.possibleCourses = possibleCourses;
    }

    public String getCareerOpportunities() {
        return careerOpportunities;
    }

    public void setCareerOpportunities(String careerOpportunities) {
        this.careerOpportunities = careerOpportunities;
    }

    public String getGovSeatAndScholarshipInfo() {
        return govSeatAndScholarshipInfo;
    }

    public void setGovSeatAndScholarshipInfo(String govSeatAndScholarshipInfo) {
        this.govSeatAndScholarshipInfo = govSeatAndScholarshipInfo;
    }

    public String getFurtherStudyOptions() {
        return furtherStudyOptions;
    }

    public void setFurtherStudyOptions(String furtherStudyOptions) {
        this.furtherStudyOptions = furtherStudyOptions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
