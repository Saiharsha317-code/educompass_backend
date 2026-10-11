package com.educompass.backend;

import com.educompass.backend.dto.RecommendationRequest;
import com.educompass.backend.entity.Career;
import com.educompass.backend.entity.DiscussionThread;
import com.educompass.backend.entity.EntranceExam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class EduCompassApiIntegrationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("1. Health Check Endpoint - GET /api/health returns UP")
    void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("UP")))
                .andExpect(jsonPath("$.service", containsString("EduCompass")))
                .andExpect(jsonPath("$.database", containsString("MySQL")));
    }

    @Test
    @DisplayName("2. Pathways Endpoint - GET /api/pathways returns seeded pathways")
    void testPathwaysList() throws Exception {
        mockMvc.perform(get("/api/pathways"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(Iterable.class)))
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(5)))
                .andExpect(jsonPath("$[?(@.code == 'MPC')].name").exists())
                .andExpect(jsonPath("$[?(@.code == 'POLYCET')].name").exists());
    }

    @Test
    @DisplayName("3. Career CRUD Lifecycle - Create, Read, Update, Delete")
    void testCareerCrudLifecycle() throws Exception {
        // Create Career
        Career newCareer = new Career();
        newCareer.setTitle("Robotics Automation Specialist");
        newCareer.setCategory("Engineering & Technology");
        newCareer.setStreamRequired("Intermediate MPC / Polytechnic");
        newCareer.setDescription("Focuses on industrial robotic arms and automated factory assembly.");
        newCareer.setWhoShouldConsider("Students who like hardware programming and robotics.");
        newCareer.setKeySkills("ROS, Python, Mechanical Design");
        newCareer.setEducationRoute("Class 10 -> Intermediate MPC -> B.Tech Mechatronics");
        newCareer.setEligibility("10+2 with MPC");
        newCareer.setEntranceExams("TGEAPCET, JEE Main");
        newCareer.setPossibleCourses("B.Tech Robotics & Automation");
        newCareer.setCareerOpportunities("Robotics Engineer at manufacturing plants");
        newCareer.setGovSeatAndScholarshipInfo("TS ePASS applicable for convenor quota");
        newCareer.setFurtherStudyOptions("M.Tech Robotics");

        String createJson = objectMapper.writeValueAsString(newCareer);

        String responseContent = mockMvc.perform(post("/api/careers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is("Robotics Automation Specialist")))
                .andReturn().getResponse().getContentAsString();

        Long createdId = objectMapper.readTree(responseContent).get("id").asLong();

        // Get by ID
        mockMvc.perform(get("/api/careers/" + createdId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(createdId.intValue())))
                .andExpect(jsonPath("$.title", is("Robotics Automation Specialist")));

        // Update Career
        newCareer.setTitle("Robotics & AI Specialist");
        String updateJson = objectMapper.writeValueAsString(newCareer);

        mockMvc.perform(put("/api/careers/" + createdId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Robotics & AI Specialist")));

        // Delete Career
        mockMvc.perform(delete("/api/careers/" + createdId))
                .andExpect(status().isNoContent());

        // Verify Delete (404)
        mockMvc.perform(get("/api/careers/" + createdId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")));
    }

    @Test
    @DisplayName("4. Validation Failure on Invalid Career - returns 400 Bad Request")
    void testInvalidCareerValidation() throws Exception {
        Career invalid = new Career(); // Missing title, category, streamRequired
        String json = objectMapper.writeValueAsString(invalid);

        mockMvc.perform(post("/api/careers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("Validation Failed")))
                .andExpect(jsonPath("$.errors.title", notNullValue()));
    }

    @Test
    @DisplayName("5. Entrance Exams - List, Filter Admission vs Recruitment, and Search")
    void testEntranceExamsListAndFilter() throws Exception {
        // All Exams
        mockMvc.perform(get("/api/exams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(5)));

        // Admission Only
        mockMvc.perform(get("/api/exams?category=ADMISSION"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].category", everyItem(is("ADMISSION"))));

        // Employment Recruitment Only
        mockMvc.perform(get("/api/exams?category=EMPLOYMENT_RECRUITMENT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].category", everyItem(is("EMPLOYMENT_RECRUITMENT"))));

        // Search exam
        mockMvc.perform(get("/api/exams?search=POLYCET"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", containsString("TGPOLYCET")));
    }

    @Test
    @DisplayName("6. Exam CRUD Lifecycle - Create, Read, Update, Delete")
    void testExamCrudLifecycle() throws Exception {
        EntranceExam exam = new EntranceExam();
        exam.setName("Test Entrance Examination");
        exam.setCategory("ADMISSION");
        exam.setTargetField("General Technical");
        exam.setApplicableStage("After Class 10");
        exam.setEligibility("10th Standard Passed");
        exam.setExamPurpose("Admission into diploma courses");
        exam.setExamPattern("100 Multiple Choice Questions");
        exam.setOfficialLink("https://example.gov.in");
        exam.setVerificationStatus("Official sample");
        exam.setNotes("Verified portal link");

        String json = objectMapper.writeValueAsString(exam);

        String res = mockMvc.perform(post("/api/exams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andReturn().getResponse().getContentAsString();

        Long examId = objectMapper.readTree(res).get("id").asLong();

        // Get by ID
        mockMvc.perform(get("/api/exams/" + examId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Entrance Examination")));

        // Update
        exam.setName("Updated Test Entrance Examination");
        mockMvc.perform(put("/api/exams/" + examId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(exam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Test Entrance Examination")));

        // Delete
        mockMvc.perform(delete("/api/exams/" + examId))
                .andExpect(status().isNoContent());

        // Verify Delete
        mockMvc.perform(get("/api/exams/" + examId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("7. Recommendation Engine - POST /api/recommendations returns valid rule-based matching")
    void testRecommendationEngine() throws Exception {
        RecommendationRequest request = new RecommendationRequest();
        request.setFavoriteSubjects(Arrays.asList("Mathematics", "Physical Science"));
        request.setInterests(Arrays.asList("Technology & Coding", "Machines & Robotics"));
        request.setWorkingStyle("practical");
        request.setStrengths(Arrays.asList("Problem Solving", "Hands-on Technical"));
        request.setPreferredDuration("MEDIUM_TERM");
        request.setBudgetPreference("PREFER_GOVT_SEATS");

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/recommendations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profileSummary", notNullValue()))
                .andExpect(jsonPath("$.topPathways", isA(Iterable.class)))
                .andExpect(jsonPath("$.topPathways.length()", greaterThanOrEqualTo(2)))
                .andExpect(jsonPath("$.topPathways[0].matchReason", notNullValue()))
                .andExpect(jsonPath("$.strategicAdvice", isA(Iterable.class)))
                .andExpect(jsonPath("$.strategicAdvice.length()", greaterThanOrEqualTo(1)));
    }

    @Test
    @DisplayName("8. Discussion Thread CRUD - Complete thread and reply operations")
    void testThreadCrudOperations() throws Exception {
        DiscussionThread thread = new DiscussionThread();
        thread.setTitle("How to prepare for TGPOLYCET Mathematics?");
        thread.setAuthor("Arjun S. (Class 10 Student)");
        thread.setCategory("Polytechnic");
        thread.setContent("Which textbook chapters have the highest weightage in Telangana SSC for Polycet?");

        String json = objectMapper.writeValueAsString(thread);

        String res = mockMvc.perform(post("/api/threads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is("How to prepare for TGPOLYCET Mathematics?")))
                .andReturn().getResponse().getContentAsString();

        Long threadId = objectMapper.readTree(res).get("id").asLong();

        // Get by ID
        mockMvc.perform(get("/api/threads/" + threadId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author", is("Arjun S. (Class 10 Student)")));

        // Add reply
        String replyJson = "{\"author\":\"Faculty Advisor\",\"content\":\"Focus on Quadratic Equations, Trigonometry, and Mensuration first!\"}";
        mockMvc.perform(post("/api/threads/" + threadId + "/replies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(replyJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.replyCount", is(1)))
                .andExpect(jsonPath("$.replies[0].author", is("Faculty Advisor")));

        // Update thread
        thread.setTitle("How to prepare for TGPOLYCET Mathematics & Physics?");
        mockMvc.perform(put("/api/threads/" + threadId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(thread)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("How to prepare for TGPOLYCET Mathematics & Physics?")));

        // Delete thread
        mockMvc.perform(delete("/api/threads/" + threadId))
                .andExpect(status().isNoContent());

        // Verify Delete
        mockMvc.perform(get("/api/threads/" + threadId))
                .andExpect(status().isNotFound());
    }
}
