package com.mahzarasua.resumeapi.education.service;

import com.mahzarasua.resumeapi.configuration.exception.CustomNotFoundException;
import com.mahzarasua.resumeapi.configuration.model.Resume;
import com.mahzarasua.resumeapi.configuration.model.School;
import com.mahzarasua.resumeapi.education.domain.EducationRequest;
import com.mahzarasua.resumeapi.education.domain.EducationResponse;
import com.mahzarasua.resumeapi.education.mapper.EducationMapper;
import com.mahzarasua.resumeapi.education.repository.EducationRepository;
import com.mahzarasua.resumeapi.education.repository.ResumeRepository;
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

import static com.mahzarasua.resumeapi.education.DummyTestEducationData.dummyEducationRequestData;
import static com.mahzarasua.resumeapi.education.DummyTestEducationData.dummySchoolData;
import static com.mahzarasua.resumeapi.education.DummyTestEducationData.generateRandomIdString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EducationServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(EducationServiceImplTest.class);

    private static EducationService service;

    private static EducationRepository repository;

    private static ResumeRepository resumeRepository;

    private static List<School> list = new ArrayList<>();
    private static final String resumeId = generateRandomIdString();
    private static final String testResource = "Education";

    @BeforeEach
    public void init(){
        service = new EducationServiceImpl();
        EducationMapper mapper = new EducationMapper();
        repository = Mockito.mock(EducationRepository.class);
        resumeRepository = Mockito.mock(ResumeRepository.class);

        for (int i = 0; i < new Random().nextInt(3)+1; i++) {
            list.add(dummySchoolData());
        }

        configureRepository();
        ReflectionTestUtils.setField(service, "resumeRepository",resumeRepository);
        ReflectionTestUtils.setField(service, "mapper", mapper);
    }

    private void configureRepository() {
        Mockito.doReturn(list)
                .when(repository).findByIdResumeId(ArgumentMatchers.anyString());
        Mockito.doReturn(Optional.of(dummySchoolData()))
                .when(repository).findById(ArgumentMatchers.any());
        ReflectionTestUtils.setField(service, "repository",repository);
    }

    private EducationRequest configureRequest() {
        return dummyEducationRequestData();
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
        EducationResponse response = service.getEducationbyResourceId(resumeId);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    public void givenResumeIdandListisNull_whenGetbyResourceId_thenThrowsException(){
        list = new ArrayList<>();
        configureRepository();
        assertThrows(CustomNotFoundException.class, () -> service.getEducationbyResourceId(resumeId));
    }

    @Test
    public void givenValidRequestandResumeRepositoryConfigured_whenSave_thenSuccess(){
        EducationRequest request = configureRequest();
        configureResumeRepository();
        service.saveEducation(request, null);
    }

    @Test
    public void givenValidRequestandResumeRepositoryNotConfigured_whenSave_thenThrowException(){
        EducationRequest request = configureRequest();
        assertThrows(CustomNotFoundException.class, () -> service.saveEducation(request, null));
    }

    @Test
    public void givenValidRequestandResumeRepositoryConfigured_whenDeletebyResumeId_thenSuccess(){
        configureRequest();
        configureResumeRepository();
        service.deleteEducationbyResumeId(resumeId);
    }

    @Test
    public void givenValidRequestandListisEmpty_whenDeletebyResumeId_thenThrowException(){
        configureRequest();

        list = new ArrayList<>();
        Mockito.doReturn(list)
                .when(repository).findByIdResumeId(ArgumentMatchers.anyString());

        Exception e = assertThrows(CustomNotFoundException.class, () -> service.deleteEducationbyResumeId(resumeId));
        assertEquals("No " + testResource + " records were found for resumeId " + resumeId, e.getMessage());
    }

    @Test
    public void givenValidRequestandResumeRepositoryNotConfigured_whenDeletebyResumeId_thenThrowException(){
        configureRequest();
        Exception e = assertThrows(CustomNotFoundException.class, () -> service.deleteEducationbyResumeId(resumeId));
        assertEquals("Resume with id ", e.getMessage().substring(0,15));
    }

    @Test
    public void givenValidRequestandResumeRepositoryNotConfigured_whenDeletebyId_thenThrowException(){
        configureRequest();
        configureResumeRepository();
        String id = generateRandomIdString();
        Mockito.doReturn(Optional.empty())
                .when(repository).findById(ArgumentMatchers.any());
        ReflectionTestUtils.setField(service, "repository",repository);
        Exception e = assertThrows(CustomNotFoundException.class, () -> service.deleteEducationbyId(resumeId, id));
        assertEquals(testResource + " with id " + id + " was not found", e.getMessage());
    }
}