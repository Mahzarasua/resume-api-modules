package com.mahzarasua.resumeapi.skills.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mahzarasua.resumeapi.configuration.util.DummyGenericTestData;
import com.mahzarasua.resumeapi.skills.domain.SkillsRequest;
import com.mahzarasua.resumeapi.skills.domain.SkillsResponse;
import com.mahzarasua.resumeapi.skills.service.SkillsService;
import com.mahzarasua.resumeapi.skills.service.SkillsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static com.mahzarasua.resumeapi.configuration.util.DataServiceUtil.OBJECT_MAPPER;
import static com.mahzarasua.resumeapi.configuration.util.DummyGenericTestData.generateRandomIdString;
import static com.mahzarasua.resumeapi.skills.DummyTestSkillsData.dummySkillRequestData;
import static com.mahzarasua.resumeapi.skills.DummyTestSkillsData.dummySkillResponseData;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SkillsControllerTest {
    private static SkillsController controller;
    private static final Logger log = LoggerFactory.getLogger(SkillsControllerTest.class);
    private static final String resumeId = DummyGenericTestData.generateRandomIdString();

    @BeforeEach
    public void init(){
        SkillsResponse response = dummySkillResponseData();
        SkillsService service = Mockito.mock(SkillsServiceImpl.class);
        Mockito.doReturn(response)
                .when(service).getSkillsbyResourceId(ArgumentMatchers.anyString());
        controller = new SkillsController();
        Mockito.doReturn(response)
                .when(service).saveSkills(ArgumentMatchers.any(), ArgumentMatchers.isNull());
        Map<String, String> mapResume = new HashMap<>();
        mapResume.put("resumeId", resumeId);
        Mockito.doReturn(mapResume)
                .when(service).deleteSkillsbyResumeId(ArgumentMatchers.anyString());
        Map<String, String> mapResumeId = new HashMap<>();
        mapResumeId.put("id", generateRandomIdString());
        mapResumeId.put("resumeId", resumeId);
        Mockito.doReturn(mapResumeId)
                .when(service).deleteSkillsbyId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        ReflectionTestUtils.setField(controller, "service", service);
    }

    @Test
    public void givenValidResumeId_whenGetListbyId_thenSuccess() throws JsonProcessingException {
        SkillsResponse response = controller.getSkillListbyId(resumeId);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidRequest_whenCreateList_thenSuccess() throws JsonProcessingException {
        SkillsRequest request = dummySkillRequestData();
        SkillsResponse response = controller.createSkillList(request);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidResumeId_whenDeleteList_thenSuccess() throws JsonProcessingException {
        Map<String, String> response = controller.deleteSkillList(resumeId, null);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidResumeIdandId_whenDeleteList_thenSuccess() throws JsonProcessingException {
        Map<String, String> response = controller.deleteSkillList(resumeId, generateRandomIdString());
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }
}