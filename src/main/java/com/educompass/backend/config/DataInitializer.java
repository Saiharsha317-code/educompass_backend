package com.educompass.backend.config;

import com.educompass.backend.entity.Career;
import com.educompass.backend.entity.DiscussionThread;
import com.educompass.backend.entity.EntranceExam;
import com.educompass.backend.entity.Pathway;
import com.educompass.backend.entity.ThreadReply;
import com.educompass.backend.repository.CareerRepository;
import com.educompass.backend.repository.DiscussionThreadRepository;
import com.educompass.backend.repository.EntranceExamRepository;
import com.educompass.backend.repository.PathwayRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PathwayRepository pathwayRepository;
    private final EntranceExamRepository examRepository;
    private final CareerRepository careerRepository;
    private final DiscussionThreadRepository threadRepository;

    public DataInitializer(PathwayRepository pathwayRepository,
                           EntranceExamRepository examRepository,
                           CareerRepository careerRepository,
                           DiscussionThreadRepository threadRepository) {
        this.pathwayRepository = pathwayRepository;
        this.examRepository = examRepository;
        this.careerRepository = careerRepository;
        this.threadRepository = threadRepository;
    }

    @Override
    public void run(String... args) {
        seedPathways();
        seedEntranceExams();
        seedCareers();
        seedDiscussionThreads();
    }

    private void seedPathways() {
        if (pathwayRepository.count() > 0) return;

        Pathway mpc = new Pathway();
        mpc.setCode("MPC");
        mpc.setName("Intermediate MPC (Mathematics, Physics, Chemistry)");
        mpc.setDuration("2 Years (Class 11 & 12)");
        mpc.setDescription("The premier analytical and STEM gateway in Telangana for engineering, architecture, pure sciences, computer science, and defence services.");
        mpc.setWhoShouldChoose("Students with high affinity for quantitative reasoning, problem-solving, abstract physics, and calculus.");
        mpc.setCoreSubjects("Mathematics (1A, 1B, 2A, 2B), Physics, Chemistry, English, and Second Language.");
        mpc.setNextSteps("Entrance exams: JEE Main/Advanced, TGEAPCET, BITSAT, NDA/NA, CUET-UG.");
        mpc.setLeadingCareers("Software Engineer, Data Scientist, Electronics Engineer, Civil Engineer, Aerospace Engineer, Defence Officer, Research Scientist.");
        mpc.setProsAndCons("Strengths: Widest flexibility in STEM disciplines. Challenges: High academic workload and intensive competitive exam prep required.");

        Pathway bipc = new Pathway();
        bipc.setCode("BIPC");
        bipc.setName("Intermediate BiPC (Biology, Physics, Chemistry)");
        bipc.setDuration("2 Years (Class 11 & 12)");
        bipc.setDescription("The quintessential pathway for medical sciences, dental, pharmacy, agriculture, veterinary, and biotechnology.");
        bipc.setWhoShouldChoose("Students curious about living organisms, human physiology, medicine, agriculture, and healthcare systems.");
        bipc.setCoreSubjects("Botany, Zoology, Physics, Chemistry, English, and Second Language.");
        bipc.setNextSteps("Entrance exams: NEET-UG, TGEAPCET (Agriculture & Pharmacy streams), ICAR/CUET.");
        bipc.setLeadingCareers("Doctor (MBBS), Dentist (BDS), Pharmacist (B.Pharm/Pharm.D), Agricultural Scientist, Microbiologist, Physiotherapist.");
        bipc.setProsAndCons("Strengths: Direct path to noble healthcare professions. Challenges: Requires high memorization, strong conceptual chemistry, and high persistence.");

        Pathway polycet = new Pathway();
        polycet.setCode("POLYCET");
        polycet.setName("Polytechnic Diploma (Engineering & Technology)");
        polycet.setDuration("3 Years (Direct Post-Class 10)");
        polycet.setDescription("Applied technical engineering diploma offering hands-on laboratory, CAD, and workshop training immediately after Class 10.");
        polycet.setWhoShouldChoose("Students who prefer practical, real-world machines, circuitry, and hands-on laboratory work over 2 years of theoretical intermediate rote learning.");
        polycet.setCoreSubjects("Trade-specific modules (CSE, ECE, EEE, Mechanical, Civil, Mining) alongside applied mathematics and sciences.");
        polycet.setNextSteps("Direct industry jobs as Junior Engineer/Technician, or lateral entry into 2nd Year B.Tech via TSECET.");
        polycet.setLeadingCareers("Junior Engineer in state/central PSUs (TS-Transco, BHEL, Railways), CAD Designer, Site Supervisor, or Graduate B.Tech Engineer.");
        polycet.setProsAndCons("Strengths: Practical skill set, early earning capability, and direct lateral entry to 2nd year B.Tech without intermediate stress.");

        Pathway mec = new Pathway();
        mec.setCode("MEC");
        mec.setName("Intermediate MEC (Mathematics, Economics, Commerce)");
        mec.setDuration("2 Years (Class 11 & 12)");
        mec.setDescription("A powerful multidisciplinary combination bridging analytical mathematics with commercial business systems and economics.");
        mec.setWhoShouldChoose("Students who enjoy numbers, financial markets, analytics, and business strategy without wanting laboratory sciences.");
        mec.setCoreSubjects("Mathematics, Commerce & Accountancy, Economics, English, and Second Language.");
        mec.setNextSteps("Professional courses: CA (Foundation), CMA, CS, B.Com (Hons), BBA, IPMAT, CUET-UG.");
        mec.setLeadingCareers("Chartered Accountant (CA), Financial Analyst, Investment Banker, Actuary, Business Consultant.");
        mec.setProsAndCons("Strengths: Exceptional corporate career progression; retains mathematics for quantitative and data roles.");

        Pathway cec = new Pathway();
        cec.setCode("CEC");
        cec.setName("Intermediate CEC (Civics, Economics, Commerce)");
        cec.setDuration("2 Years (Class 11 & 12)");
        cec.setDescription("A balanced business and social science stream emphasizing legal systems, economic fundamentals, and commerce.");
        cec.setWhoShouldChoose("Students aiming for banking, law, state civil services, business administration, and accounting without heavy calculus.");
        cec.setCoreSubjects("Civics, Economics, Commerce & Accountancy, English, and Second Language.");
        cec.setNextSteps("B.Com, BBA, 5-Year Integrated Law (CLAT/TS LAWCET), State Civil Services preparation.");
        cec.setLeadingCareers("Bank Officer, Corporate Lawyer, Tax Consultant, Entrepreneur, Public Policy Executive.");
        cec.setProsAndCons("Strengths: Manageable academic curriculum allowing dedicated preparation for competitive exams and sports.");

        Pathway arts = new Pathway();
        arts.setCode("HUMANITIES");
        arts.setName("Intermediate Humanities / Arts (HEC / Civics & History)");
        arts.setDuration("2 Years (Class 11 & 12)");
        arts.setDescription("Foundation in social sciences, governance, history, and literature, ideal for public service, law, and journalism.");
        arts.setWhoShouldChoose("Students passionate about history, policy, literature, creative writing, and public governance.");
        arts.setCoreSubjects("History, Economics, Civics / Political Science, Literature, Languages.");
        arts.setNextSteps("B.A. (Hons), Integrated Law (CLAT), Mass Communication, UPSC Civil Services preparation.");
        arts.setLeadingCareers("Civil Servant (IAS/IPS/TSPSC), Advocate/Judge, Journalist, Policy Researcher, Diplomat.");
        arts.setProsAndCons("Strengths: Deep critical thinking, reading comprehension, and strong launchpad for civil service aspirations.");

        Pathway iti = new Pathway();
        iti.setCode("ITI");
        iti.setName("Industrial Training Institute (ITI Technical Trades)");
        iti.setDuration("1 to 2 Years");
        iti.setDescription("Vocational skill training under the National Council for Vocational Training (NCVT) for specialized industrial trades.");
        iti.setWhoShouldChoose("Students seeking fast-track employment, hands-on workshop mastery, and direct technician roles.");
        iti.setCoreSubjects("Trade Theory and Practical (Electrician, Fitter, Welder, Machinist, COPA - Computer Operator).");
        iti.setNextSteps("Apprenticeship in railways/PSUs, Lateral entry to 2nd year Polytechnic Diploma, or private enterprise.");
        iti.setLeadingCareers("Certified Industrial Electrician, Plant Technician, Railway Assistant Loco Pilot (ALP), Entrepreneur.");
        iti.setProsAndCons("Strengths: Minimal cost, rapid entry into workforce, high demand in technical industries.");

        Pathway voc = new Pathway();
        voc.setCode("VOCATIONAL");
        voc.setName("Intermediate Vocational Education");
        voc.setDuration("2 Years (Class 11 & 12)");
        voc.setDescription("Telangana State Board of Intermediate Education vocational streams providing direct job-ready certifications.");
        voc.setWhoShouldChoose("Students interested in paramedical, accounting, computer applications, or agricultural hands-on diplomas.");
        voc.setCoreSubjects("Vocational trade modules (Medical Lab Technology, Physiotherapy, Office Assistantship) with bridge academics.");
        voc.setNextSteps("Bridge courses to enter standard degree programs or direct entry into clinical/office technician jobs.");
        voc.setLeadingCareers("Lab Technician, Paramedical Assistant, Junior Accountant, Agri-clinic Assistant.");
        voc.setProsAndCons("Strengths: Applied practical skill with intermediate certification.");

        pathwayRepository.saveAll(Arrays.asList(mpc, bipc, polycet, mec, cec, arts, iti, voc));
    }

    private void seedEntranceExams() {
        if (examRepository.count() > 0) return;

        // ADMISSION EXAMINATIONS
        EntranceExam tgeapcet = new EntranceExam();
        tgeapcet.setName("TGEAPCET (Telangana Engineering, Agriculture & Pharmacy Common Entrance Test)");
        tgeapcet.setCategory("ADMISSION");
        tgeapcet.setTargetField("Engineering, Agriculture, B.Pharmacy, Biotechnology");
        tgeapcet.setApplicableStage("After Intermediate (10+2)");
        tgeapcet.setEligibility("Passed or appearing in 2nd year Intermediate (MPC for Engg; BiPC for Agri/Pharma) recognized by TSBIE.");
        tgeapcet.setExamPurpose("State-level admission into University and affiliated Private Engineering, Pharmacy, and Agricultural colleges across Telangana under Convenor Quota.");
        tgeapcet.setExamPattern("Computer-based test (CBT): 160 marks (Maths/Bio: 80, Physics: 40, Chemistry: 40). No negative marking.");
        tgeapcet.setOfficialLink("https://eapcet.tsche.ac.in");
        tgeapcet.setVerificationStatus("Verified from TSCHE official schedule. Annual dates and notification released by Convenor, JNTUH.");
        tgeapcet.setNotes("Telangana resident students with convenor quota allotment may be eligible for TS ePASS tuition fee reimbursement subject to government income rules.");

        EntranceExam tgpolycet = new EntranceExam();
        tgpolycet.setName("TGPOLYCET (Telangana State Polytechnic Common Entrance Test)");
        tgpolycet.setCategory("ADMISSION");
        tgpolycet.setTargetField("Polytechnic Diploma (Engineering & Non-Engineering)");
        tgpolycet.setApplicableStage("After Class 10 (SSC)");
        tgpolycet.setEligibility("Passed SSC (Class 10) examination conducted by Telangana State Board or equivalent with Mathematics.");
        tgpolycet.setExamPurpose("Admission into 3-Year Diploma courses in Government, Government-Aided, and Private Polytechnics across Telangana.");
        tgpolycet.setExamPattern("Pen-and-paper offline OMR test: 120 marks for Engineering (Maths: 60, Physics: 30, Chemistry: 30) or 150 marks for Agri/Polytechnic (including Biology: 30).");
        tgpolycet.setOfficialLink("https://polycet.sbtet.telangana.gov.in");
        tgpolycet.setVerificationStatus("Verified from SBTET Telangana official portal.");
        tgpolycet.setNotes("Government polytechnics feature highly subsidized tuition fees and hostel accommodations.");

        EntranceExam jeeMain = new EntranceExam();
        jeeMain.setName("JEE Main & JEE Advanced");
        jeeMain.setCategory("ADMISSION");
        jeeMain.setTargetField("Engineering & Architecture (IITs, NITs, IIITs, GFTIs)");
        jeeMain.setApplicableStage("After Intermediate (10+2)");
        jeeMain.setEligibility("Passed 10+2 with Physics, Mathematics, and one of Chemistry/Biology/Technical subject. Top 2.5 lakh qualifiers in JEE Main eligible for JEE Advanced.");
        jeeMain.setExamPurpose("National level admission to premier engineering institutes across India.");
        jeeMain.setExamPattern("CBT format with MCQs and numerical value questions in Physics, Chemistry, and Mathematics.");
        jeeMain.setOfficialLink("https://jeemain.nta.nic.in");
        jeeMain.setVerificationStatus("Verified from National Testing Agency (NTA) official portal.");
        jeeMain.setNotes("NTA conducts JEE Main in two sessions annually (January & April).");

        EntranceExam neet = new EntranceExam();
        neet.setName("NEET-UG (National Eligibility cum Entrance Test)");
        neet.setCategory("ADMISSION");
        neet.setTargetField("Medical & Dental (MBBS, BDS, AYUSH, Veterinary)");
        neet.setApplicableStage("After Intermediate (10+2)");
        neet.setEligibility("Passed 10+2 with Physics, Chemistry, Biology/Biotechnology, and English with minimum 50% aggregate (relaxations apply for reserved categories).");
        neet.setExamPurpose("Single national entrance test for all medical colleges in India, including AIIMS, JIPMER, and Telangana State quota seats.");
        neet.setExamPattern("Pen and Paper test (720 marks: Physics 180, Chemistry 180, Biology 360). Negative marking applies (-1 per incorrect answer).");
        neet.setOfficialLink("https://exams.nta.ac.in/NEET");
        neet.setVerificationStatus("Verified from NTA NEET portal.");
        neet.setNotes("Counselling for Telangana state convenor quota conducted by KNRUHS (Kaloji Narayana Rao University of Health Sciences, Warangal).");

        EntranceExam clat = new EntranceExam();
        clat.setName("CLAT (Common Law Admission Test)");
        clat.setCategory("ADMISSION");
        clat.setTargetField("Law (5-Year Integrated B.A. LL.B / B.B.A. LL.B)");
        clat.setApplicableStage("After Intermediate (10+2)");
        clat.setEligibility("Passed 10+2 from any recognized board in any stream (Science, Commerce, or Arts) with minimum 45% aggregate.");
        clat.setExamPurpose("Admission into 24 National Law Universities (NLUs) in India, including NALSAR University of Law, Hyderabad.");
        clat.setExamPattern("Offline reading comprehension-based examination testing English, Current Affairs/GK, Legal Reasoning, Logical Reasoning, and Quantitative Techniques.");
        clat.setOfficialLink("https://consortiumofnlus.ac.in");
        clat.setVerificationStatus("Verified from Consortium of NLUs official portal.");
        clat.setNotes("State-level alternative for Telangana law colleges is TS LAWCET conducted by TSCHE.");

        EntranceExam nata = new EntranceExam();
        nata.setName("NATA (National Aptitude Test in Architecture)");
        nata.setCategory("ADMISSION");
        nata.setTargetField("Architecture (5-Year B.Arch)");
        nata.setApplicableStage("After Intermediate (10+2)");
        nata.setEligibility("Passed 10+2 with Physics, Chemistry, and Mathematics or 10+3 Diploma with Mathematics.");
        nata.setExamPurpose("Admission into recognized Architecture institutions across India and Telangana.");
        nata.setExamPattern("Tests architectural awareness, spatial reasoning, visual perception, drawing skills, and basic mathematics.");
        nata.setOfficialLink("https://www.nata.in");
        nata.setVerificationStatus("Verified from Council of Architecture (CoA) official portal.");
        nata.setNotes("Conducted multiple times per year by the Council of Architecture.");

        EntranceExam cuet = new EntranceExam();
        cuet.setName("CUET-UG (Common University Entrance Test)");
        cuet.setCategory("ADMISSION");
        cuet.setTargetField("Undergraduate Arts, Science, Commerce & Vocational Degrees in Central Universities");
        cuet.setApplicableStage("After Intermediate (10+2)");
        cuet.setEligibility("Passed 10+2 in relevant subjects from any recognized board.");
        cuet.setExamPurpose("Admission into Central Universities (e.g. University of Hyderabad - UoH, EFL University Hyderabad, Delhi University) and partner institutions.");
        cuet.setExamPattern("Hybrid mode testing language proficiency, domain subjects, and a general test.");
        cuet.setOfficialLink("https://cuetug.nta.nic.in");
        cuet.setVerificationStatus("Verified from NTA CUET portal.");
        cuet.setNotes("Enables students to access subsidized central university integrated master's programs.");

        EntranceExam nda = new EntranceExam();
        nda.setName("NDA / NA Examination (National Defence Academy & Naval Academy)");
        nda.setCategory("ADMISSION");
        nda.setTargetField("Defence Services (Army, Navy, Air Force Officer Cadre)");
        nda.setApplicableStage("After Intermediate (10+2)");
        nda.setEligibility("Unmarried male and female candidates aged 16.5-19.5 years. Passed 10+2 (any stream for Army wing; Physics and Maths mandatory for Air Force and Navy wings).");
        nda.setExamPurpose("Admission to 3-year military training at NDA Khadakwasla followed by commissioning as an Officer in the Armed Forces with a fully sponsored degree.");
        nda.setExamPattern("Conducted twice annually by UPSC: Written test (Maths 300 marks, General Ability Test 600 marks) followed by 5-day SSB interview and medical examination.");
        nda.setOfficialLink("https://upsc.gov.in");
        nda.setVerificationStatus("Verified from UPSC official notifications.");
        nda.setNotes("All training, accommodation, uniforms, and education are fully sponsored by the Government of India.");

        // EMPLOYMENT & RECRUITMENT EXAMINATIONS
        EntranceExam tspsc = new EntranceExam();
        tspsc.setName("TSPSC Group Services (Group 1, 2, 3, 4)");
        tspsc.setCategory("EMPLOYMENT_RECRUITMENT");
        tspsc.setTargetField("Telangana State Public Administration & Civil Services");
        tspsc.setApplicableStage("After Graduation / Intermediate (Group 4)");
        tspsc.setEligibility("Group 1-3 requires any Bachelor's degree from a recognized university. Group 4 junior assistant/clerical posts require degree or specified equivalent with computer test.");
        tspsc.setExamPurpose("Recruitment of Deputy Collectors, Commercial Tax Officers, Mandal Revenue Officers, Panchayat Secretaries, and Assistant Section Officers in Telangana State.");
        tspsc.setExamPattern("Prelims (Objective screening) followed by Mains descriptive papers in Telangana History, Economy, Constitution, and Governance (for Group 1).");
        tspsc.setOfficialLink("https://www.tspsc.gov.in");
        tspsc.setVerificationStatus("Verified from Telangana Public Service Commission official notifications.");
        tspsc.setNotes("Job notifications are published periodically depending on government department vacancies. Distinct from academic admissions.");

        EntranceExam upscCse = new EntranceExam();
        upscCse.setName("UPSC Civil Services Examination (IAS / IPS / IFS / IRS)");
        upscCse.setCategory("EMPLOYMENT_RECRUITMENT");
        upscCse.setTargetField("All India Civil Services & Central Services Group A");
        upscCse.setApplicableStage("After Graduation (Any Stream)");
        upscCse.setEligibility("Graduate from any recognized university in India (Arts, Science, Commerce, Engineering, or Medicine) aged 21-32 years (relaxations apply).");
        upscCse.setExamPurpose("Direct appointment to the premier executive administrative cadres of the Indian government.");
        upscCse.setExamPattern("Three-stage selection: Preliminary exam (GS + CSAT objective), Main examination (9 descriptive written papers), and Personality Test (Interview).");
        upscCse.setOfficialLink("https://upsc.gov.in");
        upscCse.setVerificationStatus("Verified from Union Public Service Commission annual calendar.");
        upscCse.setNotes("Employment examination, not a college entrance examination.");

        EntranceExam ssc = new EntranceExam();
        ssc.setName("Staff Selection Commission (SSC CGL & CHSL)");
        ssc.setCategory("EMPLOYMENT_RECRUITMENT");
        ssc.setTargetField("Central Government Ministries, Departments & Subordinate Offices");
        ssc.setApplicableStage("CHSL: After 10+2; CGL: After Graduation");
        ssc.setEligibility("CHSL requires 10+2 passed; CGL requires Bachelor's degree.");
        ssc.setExamPurpose("Recruitment of Income Tax Inspectors, Central Excise Inspectors, Assistant Section Officers, and Lower Division Clerks.");
        ssc.setExamPattern("Tier 1 and Tier 2 CBTs covering Quantitative Aptitude, English Comprehension, Reasoning, and General Awareness.");
        ssc.setOfficialLink("https://ssc.gov.in");
        ssc.setVerificationStatus("Verified from Staff Selection Commission official portal.");
        ssc.setNotes("Permanent central government employment opportunities with pan-India postings.");

        EntranceExam banking = new EntranceExam();
        banking.setName("Banking Examinations (IBPS PO/Clerk & SBI)");
        banking.setCategory("EMPLOYMENT_RECRUITMENT");
        banking.setTargetField("Public Sector Banking & Financial Institutions");
        banking.setApplicableStage("After Graduation (Any Stream)");
        banking.setEligibility("Any Bachelor's degree with computer literacy.");
        banking.setExamPurpose("Recruitment of Probationary Officers (PO) and Clerical cadres in 11 nationalized public sector banks and State Bank of India.");
        banking.setExamPattern("Prelims (English, Quantitative Aptitude, Reasoning) followed by Mains and Interview for PO positions.");
        banking.setOfficialLink("https://www.ibps.in");
        banking.setVerificationStatus("Verified from Institute of Banking Personnel Selection portal.");
        banking.setNotes("High regularity in annual recruitment schedules.");

        EntranceExam rrb = new EntranceExam();
        rrb.setName("Railway Recruitment Board (RRB NTPC & ALP/Technician)");
        rrb.setCategory("EMPLOYMENT_RECRUITMENT");
        rrb.setTargetField("Indian Railways (Technical, Operational & Ministerial Cadres)");
        rrb.setApplicableStage("After Class 10/ITI (ALP/Technician), 10+2 or Degree (NTPC)");
        rrb.setEligibility("Varies by post: Assistant Loco Pilot requires 10+ITI/Diploma; NTPC requires 10+2 or Degree.");
        rrb.setExamPurpose("Recruitment into India's largest public transport and logistics employer.");
        rrb.setExamPattern("Computer Based Tests (CBT 1 & CBT 2) testing General Awareness, Mathematics, General Intelligence and Reasoning, and trade technicalities.");
        rrb.setOfficialLink("https://www.rrbsecunderabad.nic.in");
        rrb.setVerificationStatus("Verified from RRB Secunderabad official notification portal.");
        rrb.setNotes("Secunderabad is the headquarters of South Central Railway covering Telangana.");

        EntranceExam teacherRecruit = new EntranceExam();
        teacherRecruit.setName("Telangana Teacher Recruitment (TS TET & TG DSC)");
        teacherRecruit.setCategory("EMPLOYMENT_RECRUITMENT");
        teacherRecruit.setTargetField("School Education (Primary & Secondary Government Teachers)");
        teacherRecruit.setApplicableStage("After D.El.Ed or B.Ed");
        teacherRecruit.setEligibility("Intermediate with D.El.Ed for Paper 1 (Primary classes 1-5); Degree with B.Ed for Paper 2 (Secondary classes 6-8). TS TET qualification mandatory for DSC.");
        teacherRecruit.setExamPurpose("Recruitment of Secondary Grade Teachers (SGT) and School Assistants (SA) in Telangana Government and Zilla Parishad schools.");
        teacherRecruit.setExamPattern("Objective test covering Child Development and Pedagogy, Language 1, Language 2 (English), Mathematics, and Science/Social Studies.");
        teacherRecruit.setOfficialLink("https://schooledu.telangana.gov.in");
        teacherRecruit.setVerificationStatus("Verified from Telangana Department of School Education.");
        teacherRecruit.setNotes("Teacher eligibility qualification followed by recruitment notifications via District Selection Committee (TG DSC).");

        examRepository.saveAll(Arrays.asList(
                tgeapcet, tgpolycet, jeeMain, neet, clat, nata, cuet, nda,
                tspsc, upscCse, ssc, banking, rrb, teacherRecruit
        ));
    }

    private void seedCareers() {
        if (careerRepository.count() > 0) return;

        Career se = new Career();
        se.setTitle("Software Engineer & Cloud Architect");
        se.setCategory("Engineering & Technology");
        se.setStreamRequired("Intermediate MPC / Polytechnic Diploma (CSE)");
        se.setDescription("Designs, builds, and maintains software applications, algorithms, and cloud systems powering modern digital economies.");
        se.setWhoShouldConsider("Students who enjoy logic puzzles, building things with computers, problem solving, and analytical thinking.");
        se.setKeySkills("Algorithmic Thinking, Programming (Java/Python), Data Structures, Cloud Computing, Teamwork.");
        se.setEducationRoute("Class 10 -> Intermediate MPC (2 yrs) OR Polytechnic Diploma CSE (3 yrs) -> B.Tech CSE / IT (4 yrs or 3 yrs lateral) -> Industry / M.Tech.");
        se.setEligibility("10+2 with Mathematics, Physics, and Chemistry or 3-year Diploma in Engineering with 50%+ marks.");
        se.setEntranceExams("TGEAPCET, JEE Main, JEE Advanced, TSECET (for diploma lateral entry).");
        se.setPossibleCourses("B.Tech in Computer Science, Artificial Intelligence, Data Science, Cyber Security.");
        se.setCareerOpportunities("Software Development Engineer (SDE), Cloud Architect, Full Stack Developer, Systems Engineer in Hyderabad IT corridor and global tech firms.");
        se.setGovSeatAndScholarshipInfo("Convenor quota in top government universities (OU, JNTUH, JNTUM) offers subsidized fees. Eligible students qualify for TS ePASS tuition fee reimbursement.");
        se.setFurtherStudyOptions("M.Tech, MS in Computer Science abroad, MBA in Tech Management.");

        Career doc = new Career();
        doc.setTitle("Medical Doctor (General Medicine & Surgery)");
        doc.setCategory("Healthcare & Allied Health");
        doc.setStreamRequired("Intermediate BiPC (Biology, Physics, Chemistry)");
        doc.setDescription("Diagnoses illnesses, performs medical procedures, provides clinical healthcare, and safeguards community health.");
        doc.setWhoShouldConsider("Students driven by empathy, interest in human anatomy, diagnostic curiosity, and endurance for continuous lifelong learning.");
        doc.setKeySkills("Clinical Diagnosis, Empathy, Calm Under Pressure, Biological Sciences, Communication.");
        doc.setEducationRoute("Class 10 -> Intermediate BiPC (2 yrs) -> NEET-UG -> MBBS (5.5 yrs including internship) -> NEET-PG -> MD / MS (3 yrs).");
        doc.setEligibility("10+2 with Biology, Physics, Chemistry, and English with minimum 50% marks in science subjects.");
        doc.setEntranceExams("NEET-UG (National Eligibility cum Entrance Test).");
        doc.setPossibleCourses("MBBS (Bachelor of Medicine, Bachelor of Surgery), followed by MD/MS specialization.");
        doc.setCareerOpportunities("Civil Assistant Surgeon in Telangana Health Services, Resident Physician, Consultant Specialist, Hospital Administrator.");
        doc.setGovSeatAndScholarshipInfo("Government medical colleges (Osmania, Gandhi, Kakatiya, and new district medical colleges) feature nominal annual tuition fees under state convenor quota.");
        doc.setFurtherStudyOptions("MD, MS, DM, M.Ch (Super-specialization), Public Health (MPH).");

        Career ca = new Career();
        ca.setTitle("Chartered Accountant & Financial Analyst");
        ca.setCategory("Commerce & Finance");
        ca.setStreamRequired("Intermediate MEC / CEC / Any Stream");
        ca.setDescription("Directs corporate audit, financial compliance, taxation strategies, business valuation, and forensic accounting.");
        ca.setWhoShouldConsider("Students who enjoy numbers, economic principles, regulatory frameworks, detail orientation, and business strategy.");
        ca.setKeySkills("Financial Accounting, Direct & Indirect Taxation, Corporate Auditing, Business Law, Quantitative Analysis.");
        ca.setEducationRoute("Class 10 -> Intermediate MEC/CEC (2 yrs) -> ICAI CA Foundation -> CA Intermediate + Articleship (2 yrs) -> CA Final.");
        ca.setEligibility("Pass in 10+2 from any recognized board. Registered with Institute of Chartered Accountants of India (ICAI).");
        ca.setEntranceExams("CA Foundation examination conducted by ICAI (held thrice a year).");
        ca.setPossibleCourses("Chartered Accountancy (ICAI), simultaneously pursued alongside B.Com / BBA.");
        ca.setCareerOpportunities("Statutory Auditor, Tax Consultant, Chief Financial Officer (CFO), Investment Banking Analyst, Private Practice.");
        ca.setGovSeatAndScholarshipInfo("CA is a self-paced professional charter under ICAI with low registration fees compared to private professional colleges.");
        ca.setFurtherStudyOptions("CFA (Chartered Financial Analyst), DISA, CPA, MBA in Finance.");

        Career civilEngineer = new Career();
        civilEngineer.setTitle("Civil & Infrastructure Engineer");
        civilEngineer.setCategory("Engineering & Technology");
        civilEngineer.setStreamRequired("Intermediate MPC / Polytechnic Diploma (Civil)");
        civilEngineer.setDescription("Plans, designs, and oversees construction of transport infrastructure, bridges, smart cities, and water management projects.");
        civilEngineer.setWhoShouldConsider("Students fascinated by structures, large scale physical construction, surveying, and urban planning.");
        civilEngineer.setKeySkills("Structural Analysis, AutoCAD, Project Management, Material Testing, Environmental Planning.");
        civilEngineer.setEducationRoute("Class 10 -> Intermediate MPC (2 yrs) OR Diploma in Civil Engineering (3 yrs) -> B.Tech Civil Engineering -> Industry / M.Tech.");
        civilEngineer.setEligibility("10+2 with Mathematics, Physics, Chemistry OR 3-year Diploma in Civil Engineering.");
        civilEngineer.setEntranceExams("TGEAPCET, JEE Main, TSECET (for lateral entry), GATE.");
        civilEngineer.setPossibleCourses("B.Tech / B.E. in Civil Engineering, Structural Engineering, Environmental Engineering.");
        civilEngineer.setCareerOpportunities("Assistant Executive Engineer (AEE) in Telangana Irrigation / R&B Departments, Infrastructure Consultant in L&T, Metro Rail.");
        civilEngineer.setGovSeatAndScholarshipInfo("Government universities (OU, JNTU) provide affordable convenor seats with full fee reimbursement eligibility for low-income brackets.");
        civilEngineer.setFurtherStudyOptions("M.Tech in Structural/Geotechnical Engineering, NICMAR Construction Management, Urban Planning.");

        Career pharma = new Career();
        pharma.setTitle("Pharmacist & Drug Development Specialist");
        pharma.setCategory("Healthcare & Allied Health");
        pharma.setStreamRequired("Intermediate BiPC or MPC");
        pharma.setDescription("Formulates, tests, manufactures, and regulates pharmaceutical drugs and medical formulations in Hyderabad's 'Genome Valley'.");
        pharma.setWhoShouldConsider("Students interested in medicinal chemistry, pharmacology, biological health, and clinical research.");
        pharma.setKeySkills("Pharmaceutical Chemistry, Quality Control, Clinical Research, Regulatory Compliance.");
        pharma.setEducationRoute("Class 10 -> Intermediate BiPC/MPC (2 yrs) -> B.Pharmacy (4 yrs) OR Pharm.D (6 yrs clinical) -> GPAT -> M.Pharmacy.");
        pharma.setEligibility("10+2 with Physics, Chemistry, and Biology/Mathematics with minimum 50% aggregate.");
        pharma.setEntranceExams("TGEAPCET (Pharmacy Stream), GPAT, NIPER JEE.");
        pharma.setPossibleCourses("B.Pharmacy, Pharm.D (Doctor of Pharmacy), M.Pharmacy.");
        pharma.setCareerOpportunities("Formulation Scientist, Drug Inspector in Drug Control Administration, Clinical Research Associate in leading pharma hubs.");
        pharma.setGovSeatAndScholarshipInfo("Hyderabad is India's leading pharmaceutical capital; university colleges and private convenor seats are covered under TS ePASS.");
        pharma.setFurtherStudyOptions("Pharm.D Post Baccalaureate, M.Pharm in Pharmacology, Regulatory Affairs, MBA Pharma.");

        Career agri = new Career();
        agri.setTitle("Agricultural Scientist & Agritech Specialist");
        agri.setCategory("Agriculture & Rural Development");
        agri.setStreamRequired("Intermediate BiPC");
        agri.setDescription("Improves crop yield, soil health, sustainable organic farming techniques, and smart drone farming technologies.");
        agri.setWhoShouldConsider("Students passionate about nature, farming technology, food security, botany, and rural development.");
        agri.setKeySkills("Crop Genetics, Soil Science, Precision Agriculture, Pest Management, Climate Adaptation.");
        agri.setEducationRoute("Class 10 -> Intermediate BiPC (2 yrs) -> TGEAPCET (Agri Stream) -> B.Sc (Hons) Agriculture (4 yrs at PJTSAU) -> M.Sc Agri / ICAR.");
        agri.setEligibility("10+2 with Biology, Physics, and Chemistry.");
        agri.setEntranceExams("TGEAPCET (Agriculture Stream), ICAR AIEEA / CUET-UG.");
        agri.setPossibleCourses("B.Sc (Hons) Agriculture, B.Sc Horticulture, B.Tech Agricultural Engineering.");
        agri.setCareerOpportunities("Agricultural Officer (AO) in Department of Agriculture, Research Fellow in ICRISAT, Agri-banking Specialist.");
        agri.setGovSeatAndScholarshipInfo("PJTSAU (Professor Jayashankar Telangana State Agricultural University) provides subsidized state government seats with rural quota reservations.");
        agri.setFurtherStudyOptions("M.Sc in Agronomy/Plant Genetics, MBA in Agribusiness Management, ICAR Scientist Examination.");

        Career civilServant = new Career();
        civilServant.setTitle("Civil Servant (IAS, IPS, TSPSC Group 1 Officer)");
        civilServant.setCategory("Public Administration & Governance");
        civilServant.setStreamRequired("Any Stream (Humanities / MPC / BiPC / MEC)");
        civilServant.setDescription("Implements government policies, administers district law and order, manages public welfare schemes, and directs state governance.");
        civilServant.setWhoShouldConsider("Students inspired by leadership, social impact, public policy, legal administration, and community upliftment.");
        civilServant.setKeySkills("Public Administration, Analytical Writing, Ethics, Governance, Social Awareness, Decisiveness.");
        civilServant.setEducationRoute("Class 10 -> Intermediate (Any stream, 2 yrs) -> Graduation in any discipline (B.A., B.Sc, B.Com, B.Tech, 3-4 yrs) -> UPSC CSE / TSPSC Group 1.");
        civilServant.setEligibility("Bachelor's degree in any discipline from a recognized university. Minimum age 21 years.");
        civilServant.setEntranceExams("UPSC Civil Services Examination, TSPSC Group 1, Group 2.");
        civilServant.setPossibleCourses("Any Bachelor's degree (B.A. Political Science/History, B.Com, B.Tech, or MBBS).");
        civilServant.setCareerOpportunities("District Collector (IAS), Superintendent of Police (IPS), Commercial Tax Officer, Revenue Divisional Officer (RDO).");
        civilServant.setGovSeatAndScholarshipInfo("Telangana State BC Study Circles and All India Civil Services coaching centers provide free coaching and stipends to eligible merit students.");
        civilServant.setFurtherStudyOptions("Master of Public Policy (MPP), In-service administrative training at LBSNAA / MCR HRD Hyderabad.");

        Career lawyer = new Career();
        lawyer.setTitle("Advocate & Corporate Legal Counsel");
        lawyer.setCategory("Law & Judiciary");
        lawyer.setStreamRequired("Any Stream (Humanities / MEC / MPC / BiPC)");
        lawyer.setDescription("Defends constitutional rights, represents clients in court, drafts corporate contracts, and interprets judicial law.");
        lawyer.setWhoShouldConsider("Students possessing persuasive speaking skills, keen logical argumentation, reading patience, and justice orientation.");
        lawyer.setKeySkills("Legal Research, Advocacy, Critical Logic, Contract Drafting, Negotiation.");
        lawyer.setEducationRoute("Class 10 -> Intermediate (Any stream, 2 yrs) -> CLAT / TS LAWCET -> 5-Year Integrated B.A. LL.B / BBA LL.B -> Bar Council of India Enrollment.");
        lawyer.setEligibility("Pass in 10+2 with minimum 45% marks from any recognized board.");
        lawyer.setEntranceExams("CLAT (for National Law Universities), TS LAWCET (for Telangana State Law Colleges), AILET.");
        lawyer.setPossibleCourses("5-Year Integrated B.A. LL.B (Hons), B.B.A. LL.B (Hons).");
        lawyer.setCareerOpportunities("High Court Advocate, Corporate Legal Consultant, Public Prosecutor, Judicial Magistrate (via Junior Civil Judge exam).");
        lawyer.setGovSeatAndScholarshipInfo("NALSAR Hyderabad is among India's top National Law Universities. State law colleges in Hyderabad and Warangal offer convenor quota via TS LAWCET.");
        lawyer.setFurtherStudyOptions("LL.M (Master of Laws), Judicial Services Examination, Cyber Law specialization.");

        Career pilot = new Career();
        pilot.setTitle("Commercial Airline Pilot & Aviation Officer");
        pilot.setCategory("Aviation & Aerospace");
        pilot.setStreamRequired("Intermediate MPC (Physics & Mathematics compulsory)");
        pilot.setDescription("Operates commercial passenger and cargo aircraft, navigates air routes, and ensures aviation safety standards.");
        pilot.setWhoShouldConsider("Students with spatial orientation, sharp reflexes, love for flight, discipline, and high situational awareness.");
        pilot.setKeySkills("Flight Navigation, Multi-tasking, Situational Awareness, Meteorology, Communication.");
        pilot.setEducationRoute("Class 10 -> Intermediate MPC (2 yrs) -> DGCA Class 1 Medical -> Flying Training Organization (200 flight hours) -> Commercial Pilot License (CPL).");
        pilot.setEligibility("10+2 with Physics and Mathematics (minimum 50% marks) and Class 1 medical fitness certificate from DGCA.");
        pilot.setEntranceExams("DGCA Ground Examinations, IGRUA Entrance Test, Air Force AFCAT (for military aviation).");
        pilot.setPossibleCourses("Commercial Pilot Training (CPL Course), B.Sc in Aviation.");
        pilot.setCareerOpportunities("First Officer / Captain in commercial airlines (IndiGo, Air India), Cargo Pilot, Corporate Jet Aviator.");
        pilot.setGovSeatAndScholarshipInfo("Military flying route via NDA is 100% sponsored by the Government of India. Civilian flying school training requires substantial private investment.");
        pilot.setFurtherStudyOptions("Type Rating (Airbus A320 / Boeing 737), Airline Transport Pilot License (ATPL), Flight Instructor.");

        Career designer = new Career();
        designer.setTitle("UI/UX & Product Designer");
        designer.setCategory("Design & Creative Arts");
        designer.setStreamRequired("Any Stream (MPC / MEC / Arts)");
        designer.setDescription("Crafts user experiences, digital interfaces, mobile app ergonomics, and industrial product designs.");
        designer.setWhoShouldConsider("Students with a blend of creative aesthetics, empathy for human users, visual styling, and problem solving.");
        designer.setKeySkills("Visual Design, Figma, User Research, Wireframing, Creative Problem Solving.");
        designer.setEducationRoute("Class 10 -> Intermediate (Any stream, 2 yrs) -> UCEED / NID DAT / NIFT -> Bachelor of Design (B.Des, 4 yrs) -> Industry.");
        designer.setEligibility("10+2 in any stream from a recognized board.");
        designer.setEntranceExams("UCEED (IIT Bombay / IIT Hyderabad), NID DAT, NIFT.");
        designer.setPossibleCourses("B.Des (Bachelor of Design) in Interaction Design, Product Design, Visual Communication.");
        designer.setCareerOpportunities("UI/UX Designer, Product Designer, Brand Identity Specialist in tech and design agencies.");
        designer.setGovSeatAndScholarshipInfo("IIT Hyderabad offers prestigious B.Des programs through UCEED. Central and state scholarships apply for qualified candidates.");
        designer.setFurtherStudyOptions("M.Des (Master of Design via CEED), Human-Computer Interaction (HCI).");

        Career armedForces = new Career();
        armedForces.setTitle("Commissioned Officer in the Indian Armed Forces");
        armedForces.setCategory("Defence & National Security");
        armedForces.setStreamRequired("Intermediate MPC / Any Stream (Army Wing)");
        armedForces.setDescription("Commands military platoons, leads tactical national defence operations, and upholds sovereignty in the Army, Navy, or Air Force.");
        armedForces.setWhoShouldConsider("Students with strong physical fitness, patriotic drive, team leadership, quick decision making, and integrity.");
        armedForces.setKeySkills("Leadership, Strategic Thinking, Physical Endurance, Crisis Management, Integrity.");
        armedForces.setEducationRoute("Class 10 -> Intermediate MPC (2 yrs) -> UPSC NDA Examination -> 5-Day SSB Interview -> NDA Khadakwasla (3 yrs) + Military Academy (1 yr) -> Lieutenant / Flying Officer.");
        armedForces.setEligibility("Unmarried candidates aged 16.5-19.5 years with 10+2 completed/appearing. (Maths/Physics required for Navy and Air Force wings).");
        armedForces.setEntranceExams("UPSC NDA & NA Examination.");
        armedForces.setPossibleCourses("B.A. / B.Sc / B.Tech awarded by Jawaharlal Nehru University (JNU) during NDA military tenure.");
        armedForces.setCareerOpportunities("Lieutenant in Indian Army, Flying Officer in Indian Air Force, Sub-Lieutenant in Indian Navy.");
        armedForces.setGovSeatAndScholarshipInfo("Complete education, boarding, uniforms, equipment, and medical care are 100% funded by the Government of India, along with officer stipend during final training.");
        armedForces.setFurtherStudyOptions("Defence Services Staff College (DSSC), Higher Command Courses, M.Tech in Defence Technology.");

        careerRepository.saveAll(Arrays.asList(
                se, doc, ca, civilEngineer, pharma, agri, civilServant, lawyer, pilot, designer, armedForces
        ));
    }

    private void seedDiscussionThreads() {
        if (threadRepository.count() > 0) return;

        DiscussionThread t1 = new DiscussionThread();
        t1.setTitle("Intermediate MPC vs Polytechnic Diploma after Class 10 in Telangana?");
        t1.setAuthor("Suresh Kumar (Parent, Warangal)");
        t1.setCategory("Educational Pathways");
        t1.setContent("My son scored 9.2 GPA in SSC Class 10. He is interested in computers and machines. Should we enroll him in Intermediate MPC at a junior college or choose Polytechnic through TGPOLYCET? What are the practical differences in learning?");
        
        ThreadReply r1 = new ThreadReply("Venkatesh R. (Career Counselor)", 
                "Both are excellent routes with different learning styles! MPC is 2 years of theoretical academics focused heavily on competitive exams (TGEAPCET/JEE). Polytechnic is 3 years of hands-on workshop, lab, and engineering diploma training. Through TSECET, polytechnic graduates can directly enter the 2nd year of B.Tech (lateral entry) into top engineering colleges!");
        ThreadReply r2 = new ThreadReply("Kavitha M. (High School Teacher)", 
                "If your son prefers hands-on practical lab work over intense theory, Polytechnic gives immediate practical skill. If he aims directly for IITs/NITs, Intermediate MPC is mandatory because JEE requires standard 10+2.");
        t1.addReply(r1);
        t1.addReply(r2);

        DiscussionThread t2 = new DiscussionThread();
        t2.setTitle("How does TS ePASS tuition fee reimbursement work for Convenor Quota?");
        t2.setAuthor("Amina Begum (Parent, Hyderabad)");
        t2.setCategory("Government Seats & Scholarships");
        t2.setContent("Can someone clarify the eligibility criteria for full tuition fee reimbursement through TS ePASS? Does getting a rank in TGEAPCET automatically mean free education?");
        
        ThreadReply r3 = new ThreadReply("Ramesh G. (Higher Education Advisor)", 
                "Important clarification: An entrance rank does NOT automatically mean free education! Fee reimbursement is governed by Telangana Government welfare department rules. Key requirements: (1) Allotment must be through Convenor Quota (counseling), (2) Student's family annual income must fall below government prescribed limits (verified via Income Certificate), and (3) Minimum 75% college attendance. Management quota seats do not qualify.");
        t2.addReply(r3);

        DiscussionThread t3 = new DiscussionThread();
        t3.setTitle("Alternative high-growth career options for BiPC students other than MBBS");
        t3.setAuthor("Sneha Reddy (Class 10 Student, Nizamabad)");
        t3.setCategory("Career Exploration");
        t3.setContent("I love Biology but I am worried about the intense competition and 10+ year commitment of MBBS. What are high-growth modern careers for BiPC students?");
        
        ThreadReply r4 = new ThreadReply("Dr. Praveen (Biotechnology Researcher)", 
                "There is a huge world beyond MBBS! Hyderabad is the vaccine and pharmaceutical capital of India. High-growth avenues include: (1) B.Sc (Hons) Agriculture via PJTSAU (great state officer opportunities), (2) Pharm.D and Clinical Research, (3) Bioinformatics and Computational Biology, (4) Food Technology, and (5) Allied Health Sciences (Radiology, Perfusion Technology, Cardiac Care).");
        t3.addReply(r4);

        threadRepository.saveAll(Arrays.asList(t1, t2, t3));
    }
}
