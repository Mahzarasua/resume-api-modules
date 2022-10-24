package com.mahzarasua.resumeapi.education.service;

import com.mahzarasua.resumeapi.configuration.exception.CustomNotFoundException;
import com.mahzarasua.resumeapi.configuration.mapper.ResumeMapper;
import com.mahzarasua.resumeapi.configuration.model.Resume;
import com.mahzarasua.resumeapi.configuration.model.School;
import com.mahzarasua.resumeapi.education.domain.EducationRequest;
import com.mahzarasua.resumeapi.education.domain.EducationResponse;
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

import static com.mahzarasua.resumeapi.education.DummyTestData.dummyEducationRequestData;
import static com.mahzarasua.resumeapi.education.DummyTestData.dummySchoolData;
import static com.mahzarasua.resumeapi.education.DummyTestData.generateRandomIdString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EducationServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(EducationServiceImplTest.class);

    private static EducationService educationService;

    private static EducationRepository repository;

    private static ResumeRepository resumeRepository;

    private static List<School> schoolList = new ArrayList<>();
    private static final String resumeId = generateRandomIdString();

    @BeforeEach
    public void init(){
        educationService = new EducationServiceImpl();
        ResumeMapper mapper = new ResumeMapper();
        repository = Mockito.mock(EducationRepository.class);
        resumeRepository = Mockito.mock(ResumeRepository.class);

        for (int i = 0; i < new Random().nextInt(3)+1; i++) {
            schoolList.add(dummySchoolData());
        }

        configureRepository();
        ReflectionTestUtils.setField(educationService, "resumeRepository",resumeRepository);
        ReflectionTestUtils.setField(educationService, "mapper", mapper);
    }

    private void configureRepository() {
        Mockito.doReturn(schoolList)
                .when(repository).findByIdResumeId(ArgumentMatchers.anyString());
        Mockito.doReturn(Optional.of(dummySchoolData()))
                .when(repository).findById(ArgumentMatchers.any());
        ReflectionTestUtils.setField(educationService, "repository",repository);
    }

    private EducationRequest configureEducationRequest() {
        return dummyEducationRequestData();
    }

    private void configureResumeRepository() {
        Resume resume = new Resume();
        resume.setId(resumeId);
        Mockito.doReturn(Optional.of(resume))
                .when(resumeRepository).findById(ArgumentMatchers.anyString());
        ReflectionTestUtils.setField(educationService, "resumeRepository",resumeRepository);
    }

    @Test
    public void givenResumeId_whenGetEducationbyResourceId_thenResponseNotNull(){
        EducationResponse response = educationService.getEducationbyResourceId(resumeId);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    public void givenResumeIdandSchoolListisNull_whenGetEducationbyResourceId_thenThrowsException(){
        schoolList = new ArrayList<>();
        configureRepository();
        assertThrows(CustomNotFoundException.class, () -> educationService.getEducationbyResourceId(resumeId));
    }

    @Test
    public void givenValidEducationRequestandResumeRepositoryConfigured_whenSaveEducation_thenSuccess(){
        EducationRequest request = configureEducationRequest();
        configureResumeRepository();
        educationService.saveEducation(request, null);
    }

    @Test
    public void givenValidEducationRequestandResumeRepositoryNotConfigured_whenSaveEducation_thenThrowException(){
        EducationRequest request = configureEducationRequest();
        assertThrows(CustomNotFoundException.class, () -> educationService.saveEducation(request, null));
    }

    @Test
    public void givenValidEducationRequestandResumeRepositoryConfigured_whenDeleteEducationbyResumeId_thenSuccess(){
        configureEducationRequest();
        configureResumeRepository();
        educationService.deleteEducationbyResumeId(resumeId);
    }

    @Test
    public void givenValidEducationRequestandSchoolListisEmpty_whenDeleteEducationbyResumeId_thenThrowException(){
        configureEducationRequest();

        schoolList = new ArrayList<>();
        Mockito.doReturn(schoolList)
                .when(repository).findByIdResumeId(ArgumentMatchers.anyString());

        Exception e = assertThrows(CustomNotFoundException.class, () -> educationService.deleteEducationbyResumeId(resumeId));
        assertEquals("No Education records were found for resumeId " + resumeId, e.getMessage());
    }

    @Test
    public void givenValidEducationRequestandResumeRepositoryNotConfigured_whenDeleteEducationbyResumeId_thenThrowException(){
        configureEducationRequest();
        Exception e = assertThrows(CustomNotFoundException.class, () -> educationService.deleteEducationbyResumeId(resumeId));
        assertEquals("Resume with id ", e.getMessage().substring(0,15));
    }

    @Test
    public void givenValidEducationRequestandResumeRepositoryNotConfigured_whenDeleteEducationbyId_thenThrowException(){
        configureEducationRequest();
        configureResumeRepository();
        String id = generateRandomIdString();
        Mockito.doReturn(Optional.empty())
                .when(repository).findById(ArgumentMatchers.any());
        ReflectionTestUtils.setField(educationService, "repository",repository);
        Exception e = assertThrows(CustomNotFoundException.class, () -> educationService.deleteEducationbyId(resumeId, id));
        assertEquals("Education with id " + id + " was not found", e.getMessage());
    }
}