package com.mahzarasua.resumeapi.workexperience;

import com.mahzarasua.resumeapi.configuration.util.DummyGenericTestData;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpRequest;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyTestWorkExpData extends DummyGenericTestData {
    private static final Logger log = LoggerFactory.getLogger(DummyTestWorkExpData.class);

    public static WorkExpResponse dummyWorkExpResponseData(){
        WorkExpResponse tmp = new WorkExpResponse();
        List<WorkExpResponse.WorkExpResponses> list = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(3)+1; i++) {
            list.add(dummyWorkExpResponseList());
        }
        tmp.setWorkExperiences(list);
        return tmp;
    }

    public static WorkExpResponse.WorkExpResponses dummyWorkExpResponseList(){
        WorkExpResponse.WorkExpResponses tmp = new WorkExpResponse.WorkExpResponses();
        tmp.setId(generateRandomIdString());
        tmp.setResumeId(generateRandomString());
        tmp.setTitle(generateRandomString());
        tmp.setCompany(generateRandomString());
        tmp.setCurrentJob(true);
        tmp.setDescription(generateRandomString());
        tmp.setStartDate(LocalDate.now());
        tmp.setEndDate(LocalDate.now());

        log.info("WorkExpResponse generated: {}", tmp);

        return tmp;
    }

    public static WorkExpRequest dummyWorkExpRequestData(){
        WorkExpRequest tmp = new WorkExpRequest();
        List<WorkExpRequest.WorkExpRequests> request = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(3)+1; i++) {
            request.add(dummyRequestList());
        }
        tmp.setWorkExperiences(request);
        return tmp;
    }

    public static WorkExpRequest.WorkExpRequests dummyRequestList(){
        WorkExpRequest.WorkExpRequests tmp = new WorkExpRequest.WorkExpRequests();
        tmp.setId(generateRandomIdString());
        tmp.setResumeId(generateRandomString());
        tmp.setTitle(generateRandomString());
        tmp.setCompany(generateRandomString());
        tmp.setCurrentJob(true);
        tmp.setDescription(generateRandomString());
        tmp.setStartDate(LocalDate.now());
        tmp.setEndDate(LocalDate.now());

        log.info("WorkExpRequest generated: {}", tmp);

        return tmp;
    }
}
