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
import static com.mahzarasua.resumeapi.education.DummyTestData.dummyEducationRequestData;
import static com.mahzarasua.resumeapi.education.DummyTestData.dummyEducationResponseData;
import static com.mahzarasua.resumeapi.education.DummyTestData.generateRandomIdString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EducationControllerTest {
    private static EducationController educationController;
    private static final Logger log = LoggerFactory.getLogger(EducationControllerTest.class);
    private static final String resumeId = generateRandomIdString();

    @BeforeEach
    public void init(){
        EducationResponse educationResponse = dummyEducationResponseData();
        EducationService educationService = Mockito.mock(EducationServiceImpl.class);
        Mockito.doReturn(educationResponse)
                .when(educationService).getEducationbyResourceId(ArgumentMatchers.anyString());
        educationController = new EducationController();
        Mockito.doReturn(educationResponse)
                .when(educationService).saveEducation(ArgumentMatchers.any(), ArgumentMatchers.isNull());
        Map<String, String> mapResume = new HashMap<>();
        mapResume.put("resumeId", resumeId);
        Mockito.doReturn(mapResume)
                .when(educationService).deleteEducationbyResumeId(ArgumentMatchers.anyString());
        Map<String, String> mapResumeId = new HashMap<>();
        mapResumeId.put("id", generateRandomIdString());
        mapResumeId.put("resumeId", resumeId);
        Mockito.doReturn(mapResumeId)
                .when(educationService).deleteEducationbyId(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        ReflectionTestUtils.setField(educationController, "service", educationService);
    }

    @Test
    public void givenValidResumeId_whenGetEducationListbyId_thenSuccess() throws JsonProcessingException {
        EducationResponse response = educationController.getEducationListbyId(resumeId);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidEducationRequest_whenCreateEducationList_thenSuccess() throws JsonProcessingException {
        EducationRequest request = dummyEducationRequestData();
        EducationResponse response = educationController.createEducationList(request);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidResumeId_whenDeleteEducationList_thenSuccess() throws JsonProcessingException {
        Map<String, String> response = educationController.deleteEducationList(resumeId, null);
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }

    @Test
    public void givenValidResumeIdandId_whenDeleteEducationList_thenSuccess() throws JsonProcessingException {
        Map<String, String> response = educationController.deleteEducationList(resumeId, generateRandomIdString());
        log.info("Response: {}", OBJECT_MAPPER.writeValueAsString(response));
        assertNotNull(response);
    }
}