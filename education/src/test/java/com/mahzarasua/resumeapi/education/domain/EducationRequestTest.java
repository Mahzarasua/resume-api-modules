package com.mahzarasua.resumeapi.education.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.mahzarasua.resumeapi.education.DummyTestData.dummyEducationRequestData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EducationRequestTest {

    private static EducationRequest educationRequest;
    private static List<EducationRequest.EducationRequests> educationList;

    private static final Logger log = LoggerFactory.getLogger(EducationRequestTest.class);

    @BeforeEach
    public void init(){
        educationRequest = dummyEducationRequestData();
        educationList = educationRequest.getEducationList();
    }

    @Test
    public void test_EducationRequest(){
        EducationRequest educationRequest1 = new EducationRequest(educationList);
        assertEquals(educationRequest, educationRequest1);
        assertThat(educationRequest1).usingRecursiveComparison().isEqualTo(educationRequest);
    }

}