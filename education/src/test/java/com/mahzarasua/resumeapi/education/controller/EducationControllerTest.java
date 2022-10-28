package com.mahzarasua.resumeapi.education.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mahzarasua.resumeapi.education.domain.EducationRequest;
import com.mahzarasua.resumeapi.education.domain.EducationResponse;
import com.mahzarasua.resumeapi.education.service.EducationService;
import com.mahzarasua.resumeapi.education.service.EducationServiceImpl;
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
import static com.mahzarasua.resumeapi.education.DummyTestEducationData.dummyEducationRequestData;
import static com.mahzarasua.resumeapi.education.DummyTestEducationData.dummyEducationResponseData;
import static com.mahzarasua.resumeapi.education.DummyTestEducationData.generateRandomIdString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EducationControllerTest {
    private static EducationController controller;
    private static final Logger log = LoggerFactory.getLogger(EducationControllerTest.class);
    private static final String resumeId = generateRandomIdString();

    @BeforeEach
    public void init(){
        EducationResponse response = dummyEducationResponseData();
        EducationService service = Mockito.mock(EducationServiceImpl.class);
        Mockito.doReturn(response)
                .when(service).getEducationbyResourceId(ArgumentMatchers.anyString());
        controller = new EducationController();
        Mockito.doReturn(response)
                .when(service).saveEducation(ArgumentMatchers.any(), ArgumentMatchers.isNull());
        Map<String, String> mapResume = new HashMap<>();
        mapResume.put("resumeId", resumeId);
        Mockito.doReturn(mapResume)
                .when(service).deleteEducationbyResumeId(ArgumentMatchers.anyString());
        Map<String, String> mapResumeId = new HashMap<>();
        mapResumeId.put("id", generateRandomIdString());
        mapResumeId.put("resumeId", resumeId);
        Mockito.doReturn(mapResumeId)
                .when(service).deleteEducationbyId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        ReflectionTestUtils.setField(controller, "service", service);
    }

    @Test
    public void givenValidResumeId_whenGetListbyId_thenSuccess() throws JsonProcessingException {
        EducationResponse response = controller.getEducationListbyId(resumeId);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidRequest_whenCreateList_thenSuccess() throws JsonProcessingException {
        EducationRequest request = dummyEducationRequestData();
        EducationResponse response = controller.createEducationList(request);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidResumeId_whenDeleteList_thenSuccess() throws JsonProcessingException {
        Map<String, String> response = controller.deleteEducationList(resumeId, null);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidResumeIdandId_whenDeleteList_thenSuccess() throws JsonProcessingException {
        Map<String, String> response = controller.deleteEducationList(resumeId, generateRandomIdString());
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }
}