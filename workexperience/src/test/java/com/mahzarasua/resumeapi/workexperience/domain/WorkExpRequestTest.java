package com.mahzarasua.resumeapi.workexperience.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.mahzarasua.resumeapi.workexperience.DummyTestWorkExpData.dummyWorkExpRequestData;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WorkExpRequestTest {

    private static WorkExpRequest r;
    private static List<WorkExpRequest.WorkExpRequests> list;

    private static final Logger log = LoggerFactory.getLogger(WorkExpRequestTest.class);

    @BeforeEach
    public void init(){
        log.info("Starting init");
        r = dummyWorkExpRequestData();
        log.info("Request generated: {}", r);
        list = r.getWorkExperiences();
        log.info("Ending init");
    }

    @Test
    public void test_Request(){
        WorkExpRequest request = new WorkExpRequest(list);
        assertEquals(r, request);
        assertThat(request).usingRecursiveComparison().isEqualTo(r);
        assertNotNull(r.toString());
    }

    @Test
    public void test_innerClass(){
        WorkExpRequest.WorkExpRequests tmp = list.get(0);
        WorkExpRequest.WorkExpRequests record = new WorkExpRequest.WorkExpRequests(
                tmp.getId(), tmp.getResumeId(), tmp.getTitle(), tmp.getCompany(), tmp.isCurrentJob()
        , tmp.getDescription(), tmp.getStartDate(), tmp.getEndDate());
        assertThat(record).usingRecursiveComparison().isEqualTo(r.getWorkExperiences().get(0));
        assertNotNull(r.toString());
        assertNotNull(list.get(0).toString());
    }

    @Test
    public void test_builder(){
        WorkExpRequest request = WorkExpRequest.builder().workExperiences(list).build();
        assertEquals(r, request);
        assertThat(request).usingRecursiveComparison().isEqualTo(r);
        assertNotNull(r.toString());
        assertNotNull(WorkExpRequest.builder().workExperiences(list).toString());
    }

}