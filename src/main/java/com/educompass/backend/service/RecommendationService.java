package com.educompass.backend.service;

import com.educompass.backend.dto.RecommendationRequest;
import com.educompass.backend.dto.RecommendationResponse;
import com.educompass.backend.dto.RecommendationResponse.CareerMatch;
import com.educompass.backend.dto.RecommendationResponse.PathwayMatch;
import com.educompass.backend.entity.Career;
import com.educompass.backend.repository.CareerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final CareerRepository careerRepository;

    public RecommendationService(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }

    public RecommendationResponse generateRecommendations(RecommendationRequest request) {
        List<String> subjects = request.getFavoriteSubjects() != null ? 
                request.getFavoriteSubjects().stream().map(String::toLowerCase).collect(Collectors.toList()) : 
                Collections.emptyList();

        List<String> interests = request.getInterests() != null ? 
                request.getInterests().stream().map(String::toLowerCase).collect(Collectors.toList()) : 
                Collections.emptyList();

        String style = request.getWorkingStyle() != null ? request.getWorkingStyle().toLowerCase() : "";
        String duration = request.getPreferredDuration() != null ? request.getPreferredDuration().toUpperCase() : "MEDIUM_TERM";
        String budget = request.getBudgetPreference() != null ? request.getBudgetPreference().toUpperCase() : "FLEXIBLE";

        // Score map for pathways
        Map<String, Integer> scores = new HashMap<>();
        Map<String, String> reasons = new HashMap<>();
        Map<String, String> focuses = new HashMap<>();
        Map<String, String> durations = new HashMap<>();

        // Initialize pathway data
        initPathway(scores, reasons, focuses, durations, "MPC", "Intermediate MPC (Maths, Physics, Chemistry)",
                "2 Years (10+2)", "Mathematics, Physics, Chemistry");
        initPathway(scores, reasons, focuses, durations, "BIPC", "Intermediate BiPC (Biology, Physics, Chemistry)",
                "2 Years (10+2)", "Biology (Botany & Zoology), Physics, Chemistry");
        initPathway(scores, reasons, focuses, durations, "POLYCET", "Polytechnic Diploma (Engineering & Technology)",
                "3 Years (Direct Technical Diploma)", "Applied Math, Engineering Physics, Workshop / Labs");
        initPathway(scores, reasons, focuses, durations, "MEC", "Intermediate MEC (Maths, Economics, Commerce)",
                "2 Years (10+2)", "Mathematics, Economics, Commerce & Accountancy");
        initPathway(scores, reasons, focuses, durations, "CEC", "Intermediate CEC (Civics, Economics, Commerce)",
                "2 Years (10+2)", "Civics, Economics, Commerce & Accountancy");
        initPathway(scores, reasons, focuses, durations, "HUMANITIES", "Intermediate Arts / Humanities (HEC / CEC)",
                "2 Years (10+2)", "History, Economics, Political Science / Civics");
        initPathway(scores, reasons, focuses, durations, "ITI", "Industrial Training Institute (ITI Trades)",
                "1-2 Years (Skill Certification)", "Trade Theory, Practical Workshops, Engineering Drawing");
        initPathway(scores, reasons, focuses, durations, "VOCATIONAL", "Intermediate Vocational Courses",
                "2 Years (Skill + Academics)", "Specialized Vocational Skills & General Academics");

        // Rule-based scoring: Subjects
        boolean likesMaths = subjects.stream().anyMatch(s -> s.contains("math") || s.contains("mathematics"));
        boolean likesPhysics = subjects.stream().anyMatch(s -> s.contains("physic") || s.contains("physical science") || s.contains("science"));
        boolean likesBiology = subjects.stream().anyMatch(s -> s.contains("bio") || s.contains("biological") || s.contains("life science"));
        boolean likesSocial = subjects.stream().anyMatch(s -> s.contains("social") || s.contains("history") || s.contains("civic") || s.contains("economic"));
        boolean likesCommerce = subjects.stream().anyMatch(s -> s.contains("commerce") || s.contains("account") || s.contains("business") || s.contains("economic"));
        boolean likesComputer = subjects.stream().anyMatch(s -> s.contains("comput") || s.contains("it") || s.contains("coding"));

        // Rule-based scoring: Interests
        boolean techInterest = interests.stream().anyMatch(i -> i.contains("tech") || i.contains("coding") || i.contains("machine") || i.contains("robot") || i.contains("computer"));
        boolean medicalInterest = interests.stream().anyMatch(i -> i.contains("health") || i.contains("doctor") || i.contains("medicine") || i.contains("bio") || i.contains("pharma"));
        boolean financeInterest = interests.stream().anyMatch(i -> i.contains("financ") || i.contains("business") || i.contains("money") || i.contains("trade") || i.contains("bank"));
        boolean lawGovInterest = interests.stream().anyMatch(i -> i.contains("law") || i.contains("civil") || i.contains("public") || i.contains("police") || i.contains("upsc") || i.contains("defence"));
        boolean designInterest = interests.stream().anyMatch(i -> i.contains("design") || i.contains("art") || i.contains("creative") || i.contains("media") || i.contains("animat"));
        boolean agriInterest = interests.stream().anyMatch(i -> i.contains("agri") || i.contains("farm") || i.contains("nature") || i.contains("environment"));

        // Apply scoring logic
        if (likesMaths) {
            scores.put("MPC", scores.get("MPC") + 35);
            scores.put("POLYCET", scores.get("POLYCET") + 25);
            scores.put("MEC", scores.get("MEC") + 30);
        }
        if (likesPhysics) {
            scores.put("MPC", scores.get("MPC") + 25);
            scores.put("POLYCET", scores.get("POLYCET") + 20);
        }
        if (likesBiology) {
            scores.put("BIPC", scores.get("BIPC") + 45);
        }
        if (likesSocial) {
            scores.put("HUMANITIES", scores.get("HUMANITIES") + 35);
            scores.put("CEC", scores.get("CEC") + 25);
        }
        if (likesCommerce) {
            scores.put("MEC", scores.get("MEC") + 35);
            scores.put("CEC", scores.get("CEC") + 40);
        }
        if (likesComputer) {
            scores.put("MPC", scores.get("MPC") + 20);
            scores.put("POLYCET", scores.get("POLYCET") + 25);
        }

        if (techInterest) {
            scores.put("MPC", scores.get("MPC") + 30);
            scores.put("POLYCET", scores.get("POLYCET") + 25);
        }
        if (medicalInterest || agriInterest) {
            scores.put("BIPC", scores.get("BIPC") + 35);
        }
        if (financeInterest) {
            scores.put("MEC", scores.get("MEC") + 30);
            scores.put("CEC", scores.get("CEC") + 25);
        }
        if (lawGovInterest) {
            scores.put("HUMANITIES", scores.get("HUMANITIES") + 30);
            scores.put("CEC", scores.get("CEC") + 20);
        }

        // Working Style rules
        if (style.contains("practical") || style.contains("hands-on")) {
            scores.put("POLYCET", scores.get("POLYCET") + 35);
            scores.put("ITI", scores.get("ITI") + 40);
            scores.put("VOCATIONAL", scores.get("VOCATIONAL") + 30);
        } else if (style.contains("analytical")) {
            scores.put("MPC", scores.get("MPC") + 25);
            scores.put("MEC", scores.get("MEC") + 20);
        } else if (style.contains("creative")) {
            scores.put("HUMANITIES", scores.get("HUMANITIES") + 20);
            scores.put("MPC", scores.get("MPC") + 15); // Architecture/Design via MPC
        } else if (style.contains("people")) {
            scores.put("BIPC", scores.get("BIPC") + 20); // Clinical/Healthcare
            scores.put("HUMANITIES", scores.get("HUMANITIES") + 20);
            scores.put("CEC", scores.get("CEC") + 15);
        }

        // Duration adjustments
        if ("SHORT_TERM".equals(duration)) {
            scores.put("ITI", scores.get("ITI") + 35);
            scores.put("POLYCET", scores.get("POLYCET") + 25);
            scores.put("VOCATIONAL", scores.get("VOCATIONAL") + 25);
        }

        // Build personalized explanations
        reasons.put("MPC", "Matches your strengths in Mathematics & Science with quantitative analytical thinking, keeping doors open for Engineering (B.Tech), Architecture, Data Science, and Defence.");
        reasons.put("BIPC", "Directly aligns with your interest in biological sciences and healthcare, serving as the required prerequisite for NEET (MBBS/BDS), Agriculture B.Sc, Pharmacy, and Allied Health.");
        reasons.put("POLYCET", "Excellent match for your practical, hands-on preference. It gives immediate industry diploma skills with lateral entry into B.Tech 2nd year via ECET without standard 10+2 stress.");
        reasons.put("MEC", "Blends analytical Mathematics with Economics and Commerce, creating the premier launchpad for CA, Data Finance, CFA, and Corporate Management.");
        reasons.put("CEC", "Offers a strong foundation in business trade, accounting principles, and economics without advanced calculus, ideal for Commerce, Banking, and Law.");
        reasons.put("HUMANITIES", "Strong alignment for critical thinking and social comprehension, providing the strongest foundation for CLAT (Law), Civil Services (UPSC / TSPSC), and Media.");
        reasons.put("ITI", "Fast-track technical training focusing 80% on practical workshop skills, enabling early government technician employment (Railways, BHEL, TS-Transco) or self-employment.");
        reasons.put("VOCATIONAL", "Structured blend of practical vocational trades and academics, suitable for immediate technical certification with option for bridge courses.");

        // Normalize scores to percentages (cap at 98%, min 40% for top choices)
        int maxScore = scores.values().stream().max(Integer::compare).orElse(100);
        if (maxScore == 0) maxScore = 1;

        final int finalMax = maxScore;
        List<PathwayMatch> topPathways = scores.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(4)
                .map(e -> {
                    int percent = Math.min(98, Math.max(50, (int) Math.round(((double) e.getValue() / finalMax) * 95)));
                    String name = getPathwayName(e.getKey());
                    return new PathwayMatch(
                            e.getKey(),
                            name,
                            percent,
                            reasons.get(e.getKey()),
                            focuses.get(e.getKey()),
                            durations.get(e.getKey())
                    );
                })
                .collect(Collectors.toList());

        // Find matching careers from Database
        List<Career> allCareers = careerRepository.findAll();
        List<CareerMatch> matchingCareers = new ArrayList<>();

        for (Career c : allCareers) {
            boolean matched = false;
            String matchReason = "";

            if (topPathways.stream().anyMatch(p -> p.getPathwayCode().equals("MPC")) && 
                (c.getStreamRequired().contains("MPC") || c.getCategory().contains("Engineering"))) {
                matched = true;
                matchReason = "Matches your high compatibility with MPC and analytical problem solving.";
            } else if (topPathways.stream().anyMatch(p -> p.getPathwayCode().equals("BIPC")) && 
                       (c.getStreamRequired().contains("BiPC") || c.getCategory().contains("Healthcare") || c.getCategory().contains("Agriculture"))) {
                matched = true;
                matchReason = "Matches your inclination towards Biological Sciences and Healthcare pathways.";
            } else if (topPathways.stream().anyMatch(p -> p.getPathwayCode().equals("POLYCET")) && 
                       (c.getStreamRequired().contains("Polytechnic") || c.getEducationRoute().contains("Diploma"))) {
                matched = true;
                matchReason = "Offers practical, lab-based technical progression into core engineering.";
            } else if (topPathways.stream().anyMatch(p -> p.getPathwayCode().equals("MEC") || p.getPathwayCode().equals("CEC")) && 
                       (c.getStreamRequired().contains("MEC") || c.getCategory().contains("Commerce"))) {
                matched = true;
                matchReason = "Aligns with your business, finance, and economics interests.";
            } else if (topPathways.stream().anyMatch(p -> p.getPathwayCode().equals("HUMANITIES")) && 
                       (c.getCategory().contains("Law") || c.getCategory().contains("Civil Services") || c.getCategory().contains("Design"))) {
                matched = true;
                matchReason = "Builds on critical analytical thinking, social studies, and public interest.";
            }

            if (matched) {
                matchingCareers.add(new CareerMatch(c.getId(), c.getTitle(), c.getCategory(), c.getStreamRequired(), matchReason));
            }
            if (matchingCareers.size() >= 5) break;
        }

        // Profile summary & strategic advice
        String summary = String.format(
                "Based on your preference for %s and interest in %s, with a %s working style, you have solid multiple pathways ahead. Notice that career success is not limited to a single rigid path.",
                String.join(", ", request.getFavoriteSubjects()),
                String.join(", ", request.getInterests()),
                style.isEmpty() ? "balanced" : style
        );

        List<String> advice = Arrays.asList(
                "Multiple Valid Routes: If you prefer engineering but want practical workshop learning, Polytechnic Diploma with ECET offers a direct route alongside standard Intermediate MPC.",
                "Verify Govt Quota & Reservations: In Telangana, convenor seats through TGEAPCET/TGPOLYCET offer subsidized tuition and TS ePASS eligibility based on family income criteria.",
                "Entrance Awareness: Check eligibility stages carefully — TGPOLYCET is written immediately after Class 10, whereas TGEAPCET/NEET are taken after Intermediate (10+2).",
                "Talk to Counselors: Discuss these options with teachers and parents before finalizing your Class 10 stream admission."
        );

        return new RecommendationResponse(summary, topPathways, matchingCareers, advice);
    }

    private void initPathway(Map<String, Integer> scores, Map<String, String> reasons,
                             Map<String, String> focuses, Map<String, String> durations,
                             String code, String name, String duration, String focus) {
        scores.put(code, 20); // base baseline
        focuses.put(code, focus);
        durations.put(code, duration);
    }

    private String getPathwayName(String code) {
        switch (code) {
            case "MPC": return "Intermediate MPC (Maths, Physics, Chemistry)";
            case "BIPC": return "Intermediate BiPC (Biology, Physics, Chemistry)";
            case "POLYCET": return "Polytechnic Diploma (Technical)";
            case "MEC": return "Intermediate MEC (Maths, Economics, Commerce)";
            case "CEC": return "Intermediate CEC (Civics, Economics, Commerce)";
            case "HUMANITIES": return "Intermediate Arts / Humanities";
            case "ITI": return "Industrial Training Institute (ITI Trades)";
            case "VOCATIONAL": return "Intermediate Vocational Education";
            default: return code;
        }
    }
}
