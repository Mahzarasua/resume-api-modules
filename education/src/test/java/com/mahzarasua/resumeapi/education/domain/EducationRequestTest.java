package com.mahzarasua.resumeapi.education.domain;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.mahzarasua.resumeapi.education.DummyTestEducationData.dummyEducationRequestData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EducationRequestTest {

    private static EducationRequest r;
    private static List<EducationRequest.EducationRequests> list;

    private static final Logger log = LoggerFactory.getLogger(EducationRequestTest.class);

    @BeforeEach
    public void init(){
        log.info("Starting init");
        r = dummyEducationRequestData();
        log.info("Request generated: {}", r);
        list = r.getEducationList();
        log.info("Ending init");
    }

    @Test
    public void test_Request(){
        EducationRequest educationRequest1 = new EducationRequest(list);
        assertEquals(r, educationRequest1);
        assertThat(educationRequest1).usingRecursiveComparison().isEqualTo(r);
    }

    @Test
    public void test_innerClass(){
        EducationRequest.EducationRequests tmp = list.get(0);
        EducationRequest.EducationRequests record = new EducationRequest.EducationRequests(tmp.getId(),
                tmp.getResumeId(), tmp.getName(), tmp.getCareer(), tmp.getDegree(),
                tmp.getStartDate(), tmp.getEndDate());
        assertEquals(r.getEducationList().get(0), record);
        AssertionsForClassTypes.assertThat(record).usingRecursiveComparison().isEqualTo(r.getEducationList().get(0));
    }

    @Test
    public void test_builder(){
        EducationRequest educationRequest1 = EducationRequest.builder().educationList(list).build();
        assertEquals(r, educationRequest1);
        AssertionsForClassTypes.assertThat(educationRequest1).usingRecursiveComparison().isEqualTo(r);
    }

}