package com.mahzarasua.resumeapi.education.controller;

import com.mahzarasua.resumeapi.EducationApp;
import com.mahzarasua.resumeapi.configuration.util.AbstractTest;
import com.mahzarasua.resumeapi.education.domain.EducationRequest;
import com.mahzarasua.resumeapi.education.domain.EducationResponse;
import com.mahzarasua.resumeapi.education.mapper.EducationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.mahzarasua.resumeapi.configuration.util.DataServiceUtil.OBJECT_MAPPER;
import static com.mahzarasua.resumeapi.configuration.util.DummyGenericTestData.generateRandomIdString;
import static com.mahzarasua.resumeapi.education.DummyTestEducationData.dummyEducationRequestData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = EducationApp.class)
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EducationControllerImplTest extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(EducationControllerImplTest.class);

    private static final String uri = "/api/v1/education/";

    private static final String resumeId = "1d84b77d-7670-4a62-adc1-5de5b24cb75d";

    private static final String idCreatedDeleted = generateRandomIdString();
    private static EducationRequest r;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        r = dummyEducationRequestData();
    }

    @Test
    @Order(1)
    public void getByResumeId() throws Exception {
        String url = uri  + resumeId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        log.info("Content: {}",OBJECT_MAPPER.writeValueAsString(content));
        EducationResponse response = super.mapFromJson(content, EducationResponse.class);
        assertFalse(response.getEducationList().isEmpty());
        log.info("Resume found with resourceId: {}", resumeId);
        log.info("Response: {}", response);
    }

    @Test
    @Order(2)
    public void createRecord() throws Exception {
        String url = uri  + resumeId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        log.info("Content: {}",super.mapToJson(content));
        EducationResponse response = super.mapFromJson(content, EducationResponse.class);
        assertFalse(response.getEducationList().isEmpty());
        log.info("Resume found with resourceId: {}", resumeId);
        log.info("Response: {}", response);

        EducationMapper map = new EducationMapper();
        EducationRequest request = map.map(response, EducationRequest.class);
        log.info("Request: {}", request);
        request.getEducationList().get(0).setId(null);
        String json = super.mapToJson(request);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        content = mvcResult.getResponse().getContentAsString();

        response = super.mapFromJson(content, EducationResponse.class);
        assertFalse(response.getEducationList().isEmpty());
        log.info("Resume found with resourceId: {}", resumeId);
        log.info("Response: {}", response);
    }

    @Test
    @Order(3)
    public void deleteRecord() throws Exception {
        String url = uri  + resumeId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        log.info("Content: {}",super.mapToJson(content));
        EducationResponse response = super.mapFromJson(content, EducationResponse.class);
        assertFalse(response.getEducationList().isEmpty());
        log.info("Resume found with resourceId: {}", resumeId);
        log.info("Response: {}", response);
        EducationMapper map = new EducationMapper();
        EducationRequest request = map.map(response, EducationRequest.class);
        log.info("Request: {}", request);

        String id = request.getEducationList().get(0).getId();

        mvcResult = mvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("id", id)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("resumeId"));
        assertTrue(content.contains("id"));
        log.info("Entry deleted for: {}", content);
    }

    @Test
    @Order(4)
    public void createDummyRecord() throws Exception {
        EducationMapper map = new EducationMapper();
        EducationRequest request = map.map(r, EducationRequest.class);
        log.info("Request: {}", request);
        request.getEducationList().get(0).setId(idCreatedDeleted);
        request.getEducationList().get(0).setResumeId(resumeId);
        String json = super.mapToJson(request);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();

        EducationResponse response = super.mapFromJson(content, EducationResponse.class);
        assertFalse(response.getEducationList().isEmpty());
        log.info("Resume found with resourceId: {}", resumeId);
        log.info("Entry created for: {}", idCreatedDeleted);
        log.info("Response: {}", response);
    }

    @Test
    @Order(5)
    public void deleteDummyRecord() throws Exception {
        String id = idCreatedDeleted;
        String url = uri  + resumeId;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("id", id)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("resumeId"));
        assertTrue(content.contains("id"));
        log.info("Entry deleted for: {}", content);
    }

}