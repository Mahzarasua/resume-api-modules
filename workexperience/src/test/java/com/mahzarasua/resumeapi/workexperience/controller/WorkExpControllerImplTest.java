package com.mahzarasua.resumeapi.workexperience.controller;

import com.mahzarasua.resumeapi.WorkExpApp;
import com.mahzarasua.resumeapi.configuration.util.AbstractTest;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpRequest;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpResponse;
import com.mahzarasua.resumeapi.workexperience.mapper.WorkExpMapper;
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
import static com.mahzarasua.resumeapi.workexperience.DummyTestWorkExpData.dummyWorkExpRequestData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = WorkExpApp.class)
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WorkExpControllerImplTest extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(WorkExpControllerImplTest.class);

    private static final String uri = "/api/v1/work/";

    private static final String resumeId = "1d84b77d-7670-4a62-adc1-5de5b24cb75d";
    private static final String idCreatedDeleted = generateRandomIdString();
    private static WorkExpRequest r;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        r = dummyWorkExpRequestData();
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
        WorkExpResponse response = super.mapFromJson(content, WorkExpResponse.class);
        assertFalse(response.getWorkExperiences().isEmpty());
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
        WorkExpResponse response = super.mapFromJson(content, WorkExpResponse.class);
        assertFalse(response.getWorkExperiences().isEmpty());
        log.info("Resume found with resourceId: {}", resumeId);
        log.info("Response: {}", response);

        WorkExpMapper map = new WorkExpMapper();
        WorkExpRequest request = map.map(response, WorkExpRequest.class);
        log.info("Request: {}", request);
        request.getWorkExperiences().get(0).setId(null);
        String json = super.mapToJson(request);
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        content = mvcResult.getResponse().getContentAsString();

        response = super.mapFromJson(content, WorkExpResponse.class);
        assertFalse(response.getWorkExperiences().isEmpty());
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
        WorkExpResponse response = super.mapFromJson(content, WorkExpResponse.class);
        assertFalse(response.getWorkExperiences().isEmpty());
        log.info("Resume found with resourceId: {}", resumeId);
        log.info("Response: {}", response);
        WorkExpMapper map = new WorkExpMapper();
        WorkExpRequest request = map.map(response, WorkExpRequest.class);
        log.info("Request: {}", request);

        String id = request.getWorkExperiences().get(0).getId();

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
        WorkExpMapper map = new WorkExpMapper();
        WorkExpRequest request = map.map(r, WorkExpRequest.class);
        log.info("Request: {}", request);
        request.getWorkExperiences().get(0).setId(idCreatedDeleted);
        request.getWorkExperiences().get(0).setResumeId(resumeId);
        String json = super.mapToJson(request);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();

        WorkExpResponse response = super.mapFromJson(content, WorkExpResponse.class);
        assertFalse(response.getWorkExperiences().isEmpty());
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