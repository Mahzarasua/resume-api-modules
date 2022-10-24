package com.mahzarasua.resumeapi.education;

import com.mahzarasua.resumeapi.configuration.model.Resume;
import com.mahzarasua.resumeapi.configuration.model.School;
import com.mahzarasua.resumeapi.education.domain.EducationRequest;
import com.mahzarasua.resumeapi.education.domain.EducationResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyTestData {
    private static final Logger log = LoggerFactory.getLogger(DummyTestData.class);

    private static final int count = 8;

    public static String generateDummyAlphabeticString(int count){
        return RandomStringUtils.randomAlphabetic(count).toUpperCase();
    }

    public static String generateDummyAlphanumericString(int count){
        return RandomStringUtils.randomAlphanumeric(count).toUpperCase();
    }

    public static String generateDummyNumericString(int count){
        return RandomStringUtils.randomNumeric(count);
    }

    public static String generateRandomIdString(){
        return generateDummyAlphanumericString(count);
    }

    public static String generateRandomString(){
        return generateDummyAlphabeticString(count);
    }

    public static EducationResponse dummyEducationResponseData(){
        EducationResponse tmp = new EducationResponse();
        List<EducationResponse.EducationResponses> educationResponsesList = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(3) + 1; i++) {
            educationResponsesList.add(dummyEducationResponseList());
        }
        tmp.setEducationList(educationResponsesList);

        return tmp;
    }

    public static EducationResponse.EducationResponses dummyEducationResponseList(){
        EducationResponse.EducationResponses tmp = new EducationResponse.EducationResponses();
        tmp.setId(generateRandomIdString());
        tmp.setResumeId(generateRandomString());
        tmp.setName(generateRandomString());
        tmp.setCareer(generateRandomString());
        tmp.setDegree(generateRandomString());
        tmp.setStartDate(LocalDate.now());
        tmp.setEndDate(LocalDate.now());

        return tmp;
    }

    public static EducationRequest dummyEducationRequestData(){
        EducationRequest tmp = new EducationRequest();
        List<EducationRequest.EducationRequests> educationRequestsList = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(3)+1; i++) {
            educationRequestsList.add(dummyEducationRequestList());
        }
        tmp.setEducationList(educationRequestsList);
        return tmp;
    }

    public static EducationRequest.EducationRequests dummyEducationRequestList(){
        EducationRequest.EducationRequests tmp = new EducationRequest.EducationRequests();
        tmp.setId(generateRandomIdString());
        tmp.setResumeId(generateRandomString());
        tmp.setName(generateRandomString());
        tmp.setCareer(generateRandomString());
        tmp.setDegree(generateRandomString());
        tmp.setStartDate(LocalDate.now());
        tmp.setEndDate(LocalDate.now());

        log.info("EducationRequest generated: {}", tmp);

        return tmp;
    }

    public static School dummySchoolData(){
        School tmp = new School();
        School.SchoolId id = new School.SchoolId();
        id.setId(generateRandomIdString());
        id.setResumeId(generateRandomString());

        tmp.setId(id);
        tmp.setName(generateRandomString());
        tmp.setCareer(generateRandomString());
        tmp.setDegree(generateRandomString());
        tmp.setStartDate(LocalDate.now());
        tmp.setEndDate(LocalDate.now());
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(dummyResumeData());
        tmp.setResume(new Resume());

        log.info("School generated: {}", tmp);

        return tmp;
    }

    public static Resume dummyResumeData(){
        Resume tmp = new Resume();
        tmp.setId(generateRandomIdString());
        tmp.setFirstName(generateRandomString());
        tmp.setLastName(generateRandomString());
        tmp.setTitle(generateRandomString());
        tmp.setCity(generateRandomString());
        tmp.setState(generateRandomString());
        tmp.setCountry(generateRandomString());
        tmp.setEmail(generateRandomString());
        tmp.setPhone(generateRandomString());
        tmp.setSummary(generateRandomString());
        tmp.setCreationDate(LocalDate.now());
        tmp.setSchools(new java.util.ArrayList<>());
        tmp.setWorkExperiences(new java.util.ArrayList<>());
        tmp.setSkills(new java.util.ArrayList<>());

        return tmp;
    }
}
