package com.educompass.backend.dto;

import java.util.List;

public class RecommendationResponse {

    private String profileSummary;
    private List<PathwayMatch> topPathways;
    private List<CareerMatch> matchingCareers;
    private List<String> strategicAdvice;

    public RecommendationResponse() {}

    public RecommendationResponse(String profileSummary, List<PathwayMatch> topPathways, List<CareerMatch> matchingCareers, List<String> strategicAdvice) {
        this.profileSummary = profileSummary;
        this.topPathways = topPathways;
        this.matchingCareers = matchingCareers;
        this.strategicAdvice = strategicAdvice;
    }

    public static class PathwayMatch {
        private String pathwayCode;
        private String pathwayName;
        private int matchScorePercentage;
        private String matchReason;
        private String keySubjectFocus;
        private String duration;

        public PathwayMatch() {}

        public PathwayMatch(String pathwayCode, String pathwayName, int matchScorePercentage, String matchReason, String keySubjectFocus, String duration) {
            this.pathwayCode = pathwayCode;
            this.pathwayName = pathwayName;
            this.matchScorePercentage = matchScorePercentage;
            this.matchReason = matchReason;
            this.keySubjectFocus = keySubjectFocus;
            this.duration = duration;
        }

        public String getPathwayCode() { return pathwayCode; }
        public void setPathwayCode(String pathwayCode) { this.pathwayCode = pathwayCode; }

        public String getPathwayName() { return pathwayName; }
        public void setPathwayName(String pathwayName) { this.pathwayName = pathwayName; }

        public int getMatchScorePercentage() { return matchScorePercentage; }
        public void setMatchScorePercentage(int matchScorePercentage) { this.matchScorePercentage = matchScorePercentage; }

        public String getMatchReason() { return matchReason; }
        public void setMatchReason(String matchReason) { this.matchReason = matchReason; }

        public String getKeySubjectFocus() { return keySubjectFocus; }
        public void setKeySubjectFocus(String keySubjectFocus) { this.keySubjectFocus = keySubjectFocus; }

        public String getDuration() { return duration; }
        public void setDuration(String duration) { this.duration = duration; }
    }

    public static class CareerMatch {
        private Long careerId;
        private String title;
        private String category;
        private String streamRequired;
        private String matchReason;

        public CareerMatch() {}

        public CareerMatch(Long careerId, String title, String category, String streamRequired, String matchReason) {
            this.careerId = careerId;
            this.title = title;
            this.category = category;
            this.streamRequired = streamRequired;
            this.matchReason = matchReason;
        }

        public Long getCareerId() { return careerId; }
        public void setCareerId(Long careerId) { this.careerId = careerId; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }

        public String getStreamRequired() { return streamRequired; }
        public void setStreamRequired(String streamRequired) { this.streamRequired = streamRequired; }

        public String getMatchReason() { return matchReason; }
        public void setMatchReason(String matchReason) { this.matchReason = matchReason; }
    }

    public String getProfileSummary() { return profileSummary; }
    public void setProfileSummary(String profileSummary) { this.profileSummary = profileSummary; }

    public List<PathwayMatch> getTopPathways() { return topPathways; }
    public void setTopPathways(List<PathwayMatch> topPathways) { this.topPathways = topPathways; }

    public List<CareerMatch> getMatchingCareers() { return matchingCareers; }
    public void setMatchingCareers(List<CareerMatch> matchingCareers) { this.matchingCareers = matchingCareers; }

    public List<String> getStrategicAdvice() { return strategicAdvice; }
    public void setStrategicAdvice(List<String> strategicAdvice) { this.strategicAdvice = strategicAdvice; }
}
