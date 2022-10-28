package com.mahzarasua.resumeapi.skills.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.mahzarasua.resumeapi.skills.DummyTestSkillsData.dummySkillResponseData;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SkillsResponseTest {

    private static SkillsResponse r;
    private static List<SkillsResponse.SkillsResponses> list;

    private static final Logger log = LoggerFactory.getLogger(SkillsResponseTest.class);

    @BeforeEach
    public void init(){
        log.info("Starting init");
        r = dummySkillResponseData();
        log.info("Request generated: {}", r);
        list = r.getSkills();
        log.info("Ending init");
    }

    @Test
    public void test_Request(){
        SkillsResponse educationRequest1 = new SkillsResponse(list);
        assertEquals(r, educationRequest1);
        assertThat(educationRequest1).usingRecursiveComparison().isEqualTo(r);
        assertNotNull(r.toString());
    }

    @Test
    public void test_innerClass(){
        SkillsResponse.SkillsResponses tmp = list.get(0);
        SkillsResponse.SkillsResponses record = new SkillsResponse.SkillsResponses(
                tmp.getId(), tmp.getResumeId(), tmp.getName(), tmp.getPercentage(), tmp.getType());
        assertEquals(r.getSkills().get(0), record);
        assertThat(record).usingRecursiveComparison().isEqualTo(r.getSkills().get(0));
        assertNotNull(r.toString());
        assertNotNull(list.get(0).toString());
    }

    @Test
    public void test_builder(){
        SkillsResponse educationRequest1 = SkillsResponse.builder().skills(list).build();
        assertEquals(r, educationRequest1);
        assertThat(educationRequest1).usingRecursiveComparison().isEqualTo(r);
        assertNotNull(r.toString());
        assertNotNull(SkillsResponse.builder().skills(list).toString());
    }

}