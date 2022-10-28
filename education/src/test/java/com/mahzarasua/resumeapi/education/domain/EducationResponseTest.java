package com.mahzarasua.resumeapi.education.domain;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.mahzarasua.resumeapi.education.DummyTestEducationData.dummyEducationResponseData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EducationResponseTest {

    private static EducationResponse r;
    private static List<EducationResponse.EducationResponses> list;

    private static final Logger log = LoggerFactory.getLogger(EducationResponseTest.class);

    @BeforeEach
    public void init(){
        log.info("Starting init");
        r = dummyEducationResponseData();
        log.info("Request generated: {}", r);
        list = r.getEducationList();
        log.info("Ending init");
    }

    @Test
    public void test_Request(){
        EducationResponse EducationResponse1 = new EducationResponse(list);
        assertEquals(r, EducationResponse1);
        assertThat(EducationResponse1).usingRecursiveComparison().isEqualTo(r);
    }

    @Test
    public void test_innerClass(){
        EducationResponse.EducationResponses tmp = list.get(0);
        EducationResponse.EducationResponses record = new EducationResponse.EducationResponses(tmp.getId(),
                tmp.getResumeId(), tmp.getName(), tmp.getCareer(), tmp.getDegree(),
                tmp.getStartDate(), tmp.getEndDate());
        assertEquals(r.getEducationList().get(0), record);
        AssertionsForClassTypes.assertThat(record).usingRecursiveComparison().isEqualTo(r.getEducationList().get(0));
    }

    @Test
    public void test_builder(){
        EducationResponse EducationResponse1 = EducationResponse.builder().educationList(list).build();
        assertEquals(r, EducationResponse1);
        AssertionsForClassTypes.assertThat(EducationResponse1).usingRecursiveComparison().isEqualTo(r);
    }

}