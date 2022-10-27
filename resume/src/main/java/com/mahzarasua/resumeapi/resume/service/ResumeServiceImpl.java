package com.mahzarasua.resumeapi.resume.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mahzarasua.resumeapi.configuration.exception.CustomNotFoundException;
import com.mahzarasua.resumeapi.configuration.model.Resume;
import com.mahzarasua.resumeapi.resume.config.KafkaProducerConfig;
import com.mahzarasua.resumeapi.resume.config.RabbitMQConfiguration;
import com.mahzarasua.resumeapi.resume.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.resume.domain.ResumeRequest;
import com.mahzarasua.resumeapi.resume.domain.ResumeResponse;
import com.mahzarasua.resumeapi.resume.domain.WorkExpResponse;
import com.mahzarasua.resumeapi.resume.mapper.CustomMapper;
import com.mahzarasua.resumeapi.resume.repository.ResumeRepository;
import com.mahzarasua.resumeapi.resume.validator.ResumeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.jms.Queue;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.mahzarasua.resumeapi.configuration.util.DataServiceUtil.OBJECT_MAPPER;
import static com.mahzarasua.resumeapi.configuration.util.DataServiceUtil.getBearerTokenHeader;
import static com.mahzarasua.resumeapi.configuration.util.DataServiceUtil.getRandomId;

@Service
@Slf4j
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomMapper mapper;

    @Autowired
    private ResumeValidator resumeValidator;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private KafkaProducerConfig producer;

    @Autowired
    @Qualifier("jmsQueue")
    private Queue queue;

    @Override
    public List<GetResumeResponse> getAllResumes() {
        List<Resume> response = resumeRepository.findAll();
        return mapper.mapAsList(response, GetResumeResponse.class);
    }

    @Override
    public GetResumeResponse getResumeById(String resourceId) {
        Resume response = resumeRepository.findById(resourceId)
                .orElseThrow(() -> new CustomNotFoundException(String.format("Resume with id %s was not found", resourceId)));

        testWorkExp(response.getId());

        return mapper.map(response, GetResumeResponse.class);
    }

    private void testWorkExp(String resumeId){
        log.info("Entering testWorkExp: {} ", resumeId);
        String token = getBearerTokenHeader();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<WorkExpResponse> workResponse;

        try{
            workResponse = restTemplate.exchange("http://WORKEXPERIENCE-API/api/v1/work/{resumeId}",
                    HttpMethod.GET,
                    entity,
                    WorkExpResponse.class,
                    resumeId
            );

            log.info("Output testWorkExp full: {}", workResponse);
            String response = null;
            try {
                response = OBJECT_MAPPER.writeValueAsString(workResponse.getBody());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            log.info("Output testWorkExp workResponse: {}", response);

            template.convertAndSend(RabbitMQConfiguration.EXCHANGE, RabbitMQConfiguration.ROUTING_KEY, workResponse.getBody());
            jmsTemplate.convertAndSend(queue, response);
            producer.sendMessage(response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ResumeResponse saveResume(ResumeRequest resumeRequest, String resourceId) {

        String id = (resourceId == null) ? getRandomId()
                : resumeRepository.findById(resourceId)
                .orElseThrow(() -> new CustomNotFoundException(String.format("Resume with id %s was not found", resourceId))).getId();

        resumeRequest.setId(id);
        validateAndSaveResume(resumeRequest);
        return new ResumeResponse(resumeRequest.getId());
    }

    @Override
    public String deleteResumebyId(String resumeId) {

        Resume response = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new CustomNotFoundException(String.format("Resume with id %s was not found", resumeId)));

        resumeRepository.delete(response);

        return resumeId;
    }

    private void validateAndSaveResume(ResumeRequest resumeRequest) {
        resumeValidator.validate(resumeRequest);
        resumeRequest.setCreationDate((resumeRequest.getCreationDate() == null) ? LocalDate.now() : resumeRequest.getCreationDate());
        setIdChildTables(resumeRequest);
        Resume resume = mapper.map(resumeRequest, Resume.class);
        resumeRepository.save(resume);
    }

    private void setIdChildTables(ResumeRequest resumeRequest) {
        if (resumeRequest.getSkills() != null)
            for (ResumeRequest.Skill skill :
                    resumeRequest.getSkills()) {
                if (skill.getId() == null) {
                    skill.setId(getRandomId());
                    skill.setResumeId(resumeRequest.getId());
                }
            }

        if (resumeRequest.getSchools() != null)
            for (ResumeRequest.School school :
                    resumeRequest.getSchools()) {
                if (school.getId() == null) {
                    school.setId(getRandomId());
                    school.setResumeId(resumeRequest.getId());
                }
            }

        if (resumeRequest.getWorkExperiences() != null)
            for (ResumeRequest.WorkExperience workExperience :
                    resumeRequest.getWorkExperiences()) {
                if (workExperience.getId() == null) {
                    workExperience.setId(getRandomId());
                    workExperience.setResumeId(resumeRequest.getId());
                }
            }
    }
}
