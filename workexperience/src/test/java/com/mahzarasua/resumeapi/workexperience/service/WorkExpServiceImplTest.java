package com.mahzarasua.resumeapi.workexperience.service;

import com.mahzarasua.resumeapi.configuration.exception.CustomNotFoundException;
import com.mahzarasua.resumeapi.configuration.model.Resume;
import com.mahzarasua.resumeapi.configuration.model.WorkExperience;
import com.mahzarasua.resumeapi.configuration.util.DummyGenericTestData;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpRequest;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpResponse;
import com.mahzarasua.resumeapi.workexperience.mapper.WorkExpMapper;
import com.mahzarasua.resumeapi.workexperience.repository.ResumeRepository;
import com.mahzarasua.resumeapi.workexperience.repository.WorkExperienceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.mahzarasua.resumeapi.configuration.util.DummyGenericTestData.dummyWorkExpData;
import static com.mahzarasua.resumeapi.workexperience.DummyTestWorkExpData.dummyWorkExpRequestData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WorkExpServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(WorkExpServiceImplTest.class);
    private static WorkExpService service;
    private static WorkExperienceRepository repository;
    private static ResumeRepository resumeRepository;
    private static List<WorkExperience> list = new ArrayList<>();
    private static final String resumeId = DummyGenericTestData.generateRandomIdString();

    private static final String testResource = "WorkExperience";

    @BeforeEach
    public void init(){
        service = new WorkExpServiceImpl();
        WorkExpMapper mapper = new WorkExpMapper();
        repository = Mockito.mock(WorkExperienceRepository.class);
        resumeRepository = Mockito.mock(ResumeRepository.class);

        for (int i = 0; i < new Random().nextInt(3)+1; i++) {
            list.add(dummyWorkExpData());
        }

        configureRepository();
        ReflectionTestUtils.setField(service, "resumeRepository",resumeRepository);
        ReflectionTestUtils.setField(service, "mapper", mapper);
    }

    private void configureRepository() {
        Mockito.doReturn(list)
                .when(repository).findByIdResumeId(ArgumentMatchers.anyString());
        Mockito.doReturn(Optional.of(dummyWorkExpData()))
                .when(repository).findById(ArgumentMatchers.any());
        ReflectionTestUtils.setField(service, "repository",repository);
    }

    private WorkExpRequest configureRequest() {
        return dummyWorkExpRequestData();
    }

    private void configureResumeRepository() {
        Resume resume = new Resume();
        resume.setId(resumeId);
        Mockito.doReturn(Optional.of(resume))
                .when(resumeRepository).findById(ArgumentMatchers.anyString());
        ReflectionTestUtils.setField(service, "resumeRepository",resumeRepository);
    }

    @Test
    public void givenResumeId_whenGetbyResourceId_thenResponseNotNull(){
        WorkExpResponse response = service.getWorkExpbyResourceId(resumeId);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    public void givenResumeIdandListisEmpty_whenGetbyResourceId_thenThrowsException(){
        list = new ArrayList<>();
        configureRepository();
        assertThrows(CustomNotFoundException.class, () -> service.getWorkExpbyResourceId(resumeId));
    }

    @Test
    public void givenValidRequestandResumeRepositoryConfigured_whenSave_thenSuccess(){
        WorkExpRequest request = configureRequest();
        configureResumeRepository();
        service.saveWorkExp(request, null);
    }

    @Test
    public void givenValidRequestandResumeRepositoryNotConfigured_whenSave_thenThrowException(){
        WorkExpRequest request = configureRequest();
        assertThrows(CustomNotFoundException.class, () -> service.saveWorkExp(request, null));
    }

    @Test
    public void givenValidRequestandResumeRepositoryConfigured_whenDeletebyResumeId_thenSuccess(){
        configureRequest();
        configureResumeRepository();
        service.deleteWorkExpbyResumeId(resumeId);
    }

    @Test
    public void givenValidRequestandListisEmpty_whenDeletebyResumeId_thenThrowException(){
        configureRequest();

        list = new ArrayList<>();
        Mockito.doReturn(list)
                .when(repository).findByIdResumeId(ArgumentMatchers.anyString());

        Exception e = assertThrows(CustomNotFoundException.class, () -> service.deleteWorkExpbyResumeId(resumeId));
        assertEquals("No " + testResource + " records were found for resumeId " + resumeId, e.getMessage());
    }

    @Test
    public void givenValidRequestandResumeRepositoryNotConfigured_whenDeletebyResumeId_thenThrowException(){
        configureRequest();
        Exception e = assertThrows(CustomNotFoundException.class, () -> service.deleteWorkExpbyResumeId(resumeId));
        assertEquals("Resume with id ", e.getMessage().substring(0,15));
    }

    @Test
    public void givenValidRequestandResumeRepositoryNotConfigured_whenDeletebyId_thenThrowException(){
        configureRequest();
        configureResumeRepository();
        String id = DummyGenericTestData.generateRandomIdString();
        Mockito.doReturn(Optional.empty())
                .when(repository).findById(ArgumentMatchers.any());
        ReflectionTestUtils.setField(service, "repository",repository);
        Exception e = assertThrows(CustomNotFoundException.class, () -> service.deleteWorkExpbyId(resumeId, id));
        assertEquals(testResource + " with id " + id + " was not found", e.getMessage());
    }

}