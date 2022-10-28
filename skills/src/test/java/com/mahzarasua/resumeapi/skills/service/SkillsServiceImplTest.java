package com.mahzarasua.resumeapi.skills.service;

import com.mahzarasua.resumeapi.configuration.exception.CustomNotFoundException;
import com.mahzarasua.resumeapi.configuration.model.Resume;
import com.mahzarasua.resumeapi.configuration.model.Skill;
import com.mahzarasua.resumeapi.skills.domain.SkillsRequest;
import com.mahzarasua.resumeapi.skills.domain.SkillsResponse;
import com.mahzarasua.resumeapi.skills.mapper.SkillsMapper;
import com.mahzarasua.resumeapi.skills.repository.ResumeRepository;
import com.mahzarasua.resumeapi.skills.repository.SkillsRepository;
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

import static com.mahzarasua.resumeapi.skills.DummyTestSkillsData.dummySkillData;
import static com.mahzarasua.resumeapi.skills.DummyTestSkillsData.dummySkillRequestData;
import static com.mahzarasua.resumeapi.skills.DummyTestSkillsData.generateRandomIdString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SkillsServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(SkillsServiceImplTest.class);
    private static SkillsService service;
    private static SkillsRepository repository;
    private static ResumeRepository resumeRepository;
    private static List<Skill> list = new ArrayList<>();
    private static final String resumeId = generateRandomIdString();

    private static final String testResource = "Skill";

    @BeforeEach
    public void init(){
        service = new SkillsServiceImpl();
        SkillsMapper mapper = new SkillsMapper();
        repository = Mockito.mock(SkillsRepository.class);
        resumeRepository = Mockito.mock(ResumeRepository.class);

        for (int i = 0; i < new Random().nextInt(3)+1; i++) {
            list.add(dummySkillData());
        }

        configureRepository();
        ReflectionTestUtils.setField(service, "resumeRepository",resumeRepository);
        ReflectionTestUtils.setField(service, "mapper", mapper);
    }

    private void configureRepository() {
        Mockito.doReturn(list)
                .when(repository).findByIdResumeId(ArgumentMatchers.anyString());
        Mockito.doReturn(Optional.of(dummySkillData()))
                .when(repository).findById(ArgumentMatchers.any());
        ReflectionTestUtils.setField(service, "repository",repository);
    }

    private SkillsRequest configureRequest() {
        return dummySkillRequestData();
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
        SkillsResponse response = service.getSkillsbyResourceId(resumeId);
        log.info("Response: {}", response);
        assertNotNull(response);
    }

    @Test
    public void givenResumeIdandListisEmpty_whenGetbyResourceId_thenThrowsException(){
        list = new ArrayList<>();
        configureRepository();
        assertThrows(CustomNotFoundException.class, () -> service.getSkillsbyResourceId(resumeId));
    }

    @Test
    public void givenValidRequestandResumeRepositoryConfigured_whenSave_thenSuccess(){
        SkillsRequest request = configureRequest();
        configureResumeRepository();
        service.saveSkills(request, null);
    }

    @Test
    public void givenValidRequestandResumeRepositoryNotConfigured_whenSave_thenThrowException(){
        SkillsRequest request = configureRequest();
        assertThrows(CustomNotFoundException.class, () -> service.saveSkills(request, null));
    }

    @Test
    public void givenValidRequestandResumeRepositoryConfigured_whenDeletebyResumeId_thenSuccess(){
        configureRequest();
        configureResumeRepository();
        service.deleteSkillsbyResumeId(resumeId);
    }

    @Test
    public void givenValidRequestandListisEmpty_whenDeletebyResumeId_thenThrowException(){
        configureRequest();

        list = new ArrayList<>();
        Mockito.doReturn(list)
                .when(repository).findByIdResumeId(ArgumentMatchers.anyString());

        Exception e = assertThrows(CustomNotFoundException.class, () -> service.deleteSkillsbyResumeId(resumeId));
        assertEquals("No " + testResource + " records were found for resumeId " + resumeId, e.getMessage());
    }

    @Test
    public void givenValidRequestandResumeRepositoryNotConfigured_whenDeletebyResumeId_thenThrowException(){
        configureRequest();
        Exception e = assertThrows(CustomNotFoundException.class, () -> service.deleteSkillsbyResumeId(resumeId));
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
        Exception e = assertThrows(CustomNotFoundException.class, () -> service.deleteSkillsbyId(resumeId, id));
        assertEquals(testResource + " with id " + id + " was not found", e.getMessage());
    }

}