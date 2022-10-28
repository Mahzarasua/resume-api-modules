package com.mahzarasua.resumeapi.education;

import com.mahzarasua.resumeapi.configuration.util.DummyGenericTestData;
import com.mahzarasua.resumeapi.education.domain.EducationRequest;
import com.mahzarasua.resumeapi.education.domain.EducationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyTestEducationData extends DummyGenericTestData {
    private static final Logger log = LoggerFactory.getLogger(DummyTestEducationData.class);

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
}
