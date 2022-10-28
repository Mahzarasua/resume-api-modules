package com.mahzarasua.resumeapi.workexperience.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.mahzarasua.resumeapi.workexperience.DummyTestWorkExpData.dummyWorkExpResponseData;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WorkExpResponseTest {

    private static WorkExpResponse r;
    private static List<WorkExpResponse.WorkExpResponses> list;

    private static final Logger log = LoggerFactory.getLogger(WorkExpResponseTest.class);

    @BeforeEach
    public void init(){
        log.info("Starting init");
        r = dummyWorkExpResponseData();
        log.info("Request generated: {}", r);
        list = r.getWorkExperiences();
        log.info("Ending init");
    }

    @Test
    public void test_Request(){
        WorkExpResponse request = new WorkExpResponse(list);
        assertEquals(r, request);
        assertThat(request).usingRecursiveComparison().isEqualTo(r);
        assertNotNull(r.toString());
    }

    @Test
    public void test_innerClass(){
        WorkExpResponse.WorkExpResponses tmp = list.get(0);
        WorkExpResponse.WorkExpResponses record = new WorkExpResponse.WorkExpResponses(
                tmp.getId(), tmp.getResumeId(), tmp.getTitle(), tmp.getCompany(), tmp.isCurrentJob()
        , tmp.getDescription(), tmp.getStartDate(), tmp.getEndDate());
        assertThat(record).usingRecursiveComparison().isEqualTo(r.getWorkExperiences().get(0));
        assertNotNull(r.toString());
        assertNotNull(list.get(0).toString());
    }

    @Test
    public void test_builder(){
        WorkExpResponse request = WorkExpResponse.builder().workExperiences(list).build();
        assertEquals(r, request);
        assertThat(request).usingRecursiveComparison().isEqualTo(r);
        assertNotNull(r.toString());
        assertNotNull(WorkExpResponse.builder().workExperiences(list).toString());
    }

}