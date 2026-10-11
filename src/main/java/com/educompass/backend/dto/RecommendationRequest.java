package com.educompass.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class RecommendationRequest {

    @NotEmpty(message = "At least one favorite subject must be selected")
    private List<String> favoriteSubjects;

    @NotEmpty(message = "At least one interest must be selected")
    private List<String> interests;

    @NotBlank(message = "Working style is required")
    private String workingStyle; // practical, analytical, creative, theoretical, people-oriented

    private List<String> strengths;

    private String preferredDuration; // SHORT_TERM, MEDIUM_TERM, LONG_TERM

    private String budgetPreference; // GOVT_SUBSIDIZED, MODERATE, FLEXIBLE

    public RecommendationRequest() {}

    public List<String> getFavoriteSubjects() {
        return favoriteSubjects;
    }

    public void setFavoriteSubjects(List<String> favoriteSubjects) {
        this.favoriteSubjects = favoriteSubjects;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public String getWorkingStyle() {
        return workingStyle;
    }

    public void setWorkingStyle(String workingStyle) {
        this.workingStyle = workingStyle;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public String getPreferredDuration() {
        return preferredDuration;
    }

    public void setPreferredDuration(String preferredDuration) {
        this.preferredDuration = preferredDuration;
    }

    public String getBudgetPreference() {
        return budgetPreference;
    }

    public void setBudgetPreference(String budgetPreference) {
        this.budgetPreference = budgetPreference;
    }
}
