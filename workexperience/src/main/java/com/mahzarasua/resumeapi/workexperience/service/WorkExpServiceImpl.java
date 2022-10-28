package com.mahzarasua.resumeapi.workexperience.service;

import com.mahzarasua.resumeapi.configuration.exception.CustomNotFoundException;
import com.mahzarasua.resumeapi.configuration.model.WorkExperience;
import com.mahzarasua.resumeapi.configuration.model.WorkExperience.WorkExperienceId;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpRequest;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpResponse;
import com.mahzarasua.resumeapi.workexperience.mapper.WorkExpMapper;
import com.mahzarasua.resumeapi.workexperience.repository.ResumeRepository;
import com.mahzarasua.resumeapi.workexperience.repository.WorkExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mahzarasua.resumeapi.configuration.util.DataServiceUtil.getRandomId;

@Service
public class WorkExpServiceImpl implements WorkExpService {

    @Autowired
    private WorkExperienceRepository repository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private WorkExpMapper mapper;

    @Override
    public WorkExpResponse getWorkExpbyResourceId(String resumeId) {
        List<WorkExperience> list = repository.findByIdResumeId(resumeId);
        if (list.isEmpty())
            throw new CustomNotFoundException(String.format("Resume with id %s was not found", resumeId));

        List<WorkExpResponse.WorkExpResponses> workExps = mapper.mapAsList(list, WorkExpResponse.WorkExpResponses.class);
        WorkExpResponse response = new WorkExpResponse();
        response.setWorkExperiences(workExps);
        return response;
    }

    @Override
    public WorkExpResponse saveWorkExp(WorkExpRequest request, String uuid) {
        WorkExperienceId id = new WorkExperienceId(request.getWorkExperiences().get(0).getId()
                , request.getWorkExperiences().get(0).getResumeId());

        String resumeId = resumeRepository.findById(id.getResumeId())
                .orElseThrow(() -> new CustomNotFoundException(String.format("Resume with id %s was not found", id.getResumeId())))
                .getId();

        for (WorkExpRequest.WorkExpRequests row :
                request.getWorkExperiences()) {
            row.setResumeId(resumeId);
            if (row.getId() == null) row.setId(getRandomId());
        }

        List<WorkExperience> response = mapper.mapAsList(request.getWorkExperiences(), WorkExperience.class);

        for (WorkExperience record :
                response) {
            repository.save(record);
        }

        return getWorkExpbyResourceId(resumeId);
    }

    @Override
    public Map<String, String> deleteWorkExpbyResumeId(String resumeId) {
        List<WorkExperience> response = repository.findByIdResumeId(resumeId);

        if(response.size() == 0)
            throw new CustomNotFoundException(String.format("No WorkExperience records were found for resumeId %s", resumeId));

        for (WorkExperience record :
                response)
            deleteWorkExpbyId(record.getId().getResumeId(), record.getId().getId());

        Map<String, String> map = new HashMap<>();
        map.put("resumeId", resumeId);

        return map;
    }

    @Override
    public Map<String, String> deleteWorkExpbyId(String resumeId, String id) {
        WorkExperienceId wid = new WorkExperienceId(id, resumeId);

        resumeRepository.findById(wid.getResumeId())
                .orElseThrow(() -> new CustomNotFoundException(String.format("Resume with id %s was not found", wid.getResumeId())))
                .getId();

        WorkExperience response = repository.findById(wid)
                .orElseThrow(() -> new CustomNotFoundException(String.format("WorkExperience with id %s was not found", wid.getId())));

        repository.delete(response);
        Map<String, String> map = new HashMap<>();
        map.put("id", wid.getId());
        map.put("resumeId", wid.getResumeId());

        return map;
    }
}
