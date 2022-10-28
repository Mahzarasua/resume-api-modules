package com.mahzarasua.resumeapi.workexperience.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mahzarasua.resumeapi.configuration.util.DummyGenericTestData;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpRequest;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpResponse;
import com.mahzarasua.resumeapi.workexperience.service.WorkExpService;
import com.mahzarasua.resumeapi.workexperience.service.WorkExpServiceImpl;
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
import static com.mahzarasua.resumeapi.workexperience.DummyTestWorkExpData.dummyWorkExpRequestData;
import static com.mahzarasua.resumeapi.workexperience.DummyTestWorkExpData.dummyWorkExpResponseData;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WorkExpControllerTest {
    private static WorkExpController controller;
    private static final Logger log = LoggerFactory.getLogger(WorkExpControllerTest.class);
    private static final String resumeId = DummyGenericTestData.generateRandomIdString();

    @BeforeEach
    public void init(){
        WorkExpResponse response = dummyWorkExpResponseData();
        WorkExpService service = Mockito.mock(WorkExpServiceImpl.class);
        Mockito.doReturn(response)
                .when(service).getWorkExpbyResourceId(ArgumentMatchers.anyString());
        controller = new WorkExpController();
        Mockito.doReturn(response)
                .when(service).saveWorkExp(ArgumentMatchers.any(), ArgumentMatchers.isNull());
        Map<String, String> mapResume = new HashMap<>();
        mapResume.put("resumeId", resumeId);
        Mockito.doReturn(mapResume)
                .when(service).deleteWorkExpbyResumeId(ArgumentMatchers.anyString());
        Map<String, String> mapResumeId = new HashMap<>();
        mapResumeId.put("id", generateRandomIdString());
        mapResumeId.put("resumeId", resumeId);
        Mockito.doReturn(mapResumeId)
                .when(service).deleteWorkExpbyId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        ReflectionTestUtils.setField(controller, "service", service);
    }

    @Test
    public void givenValidResumeId_whenGetListbyId_thenSuccess() throws JsonProcessingException {
        WorkExpResponse response = controller.getWorkExpsbyId(resumeId);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidRequest_whenCreateList_thenSuccess() throws JsonProcessingException {
        WorkExpRequest request = dummyWorkExpRequestData();
        WorkExpResponse response = controller.createWorkExps(request);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidResumeId_whenDeleteList_thenSuccess() throws JsonProcessingException {
        Map<String, String> response = controller.deleteWorkExps(resumeId, null);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidResumeIdandId_whenDeleteList_thenSuccess() throws JsonProcessingException {
        Map<String, String> response = controller.deleteWorkExps(resumeId, generateRandomIdString());
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }
}