package com.mahzarasua.resumeapi.skills.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.mahzarasua.resumeapi.skills.DummyTestSkillsData.dummySkillRequestData;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SkillsRequestTest {

    private static SkillsRequest r;
    private static List<SkillsRequest.SkillsRequests> list;

    private static final Logger log = LoggerFactory.getLogger(SkillsRequestTest.class);

    @BeforeEach
    public void init(){
        log.info("Starting init");
        r = dummySkillRequestData();
        log.info("Request generated: {}", r);
        list = r.getSkills();
        log.info("Ending init");
    }

    @Test
    public void test_Request(){
        SkillsRequest educationRequest1 = new SkillsRequest(list);
        assertEquals(r, educationRequest1);
        assertThat(educationRequest1).usingRecursiveComparison().isEqualTo(r);
        assertNotNull(r.toString());
    }

    @Test
    public void test_innerClass(){
        SkillsRequest.SkillsRequests tmp = list.get(0);
        SkillsRequest.SkillsRequests record = new SkillsRequest.SkillsRequests(
                tmp.getId(), tmp.getResumeId(), tmp.getName(), tmp.getPercentage(), tmp.getType());
        assertEquals(r.getSkills().get(0), record);
        assertThat(record).usingRecursiveComparison().isEqualTo(r.getSkills().get(0));
        assertNotNull(r.toString());
        assertNotNull(list.get(0).toString());
    }

    @Test
    public void test_builder(){
        SkillsRequest educationRequest1 = SkillsRequest.builder().skills(list).build();
        assertEquals(r, educationRequest1);
        assertThat(educationRequest1).usingRecursiveComparison().isEqualTo(r);
        assertNotNull(r.toString());
        assertNotNull(SkillsRequest.builder().skills(list).toString());
    }

}