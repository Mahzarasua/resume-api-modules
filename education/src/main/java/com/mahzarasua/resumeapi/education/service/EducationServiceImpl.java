package com.mahzarasua.resumeapi.education.service;

import com.mahzarasua.resumeapi.configuration.exception.CustomNotFoundException;
import com.mahzarasua.resumeapi.configuration.mapper.ResumeMapper;
import com.mahzarasua.resumeapi.education.domain.EducationRequest;
import com.mahzarasua.resumeapi.education.domain.EducationResponse;
import com.mahzarasua.resumeapi.education.repository.EducationRepository;
import com.mahzarasua.resumeapi.education.repository.ResumeRepository;
import com.mahzarasua.resumeapi.configuration.model.School;
import com.mahzarasua.resumeapi.configuration.model.School.SchoolId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mahzarasua.resumeapi.configuration.util.DataServiceUtil.getRandomId;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationRepository repository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ResumeMapper mapper;

    @Override
    public EducationResponse getEducationbyResourceId(String resumeId) {
        List<School> list = repository.findByIdResumeId(resumeId);

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format("Resume with id %s was not found", resumeId));

        List<EducationResponse.EducationResponses> educationList = mapper.mapAsList(list, EducationResponse.EducationResponses.class);
        EducationResponse response = new EducationResponse();
        response.setEducationList(educationList);
        return response;
    }

    @Override
    public EducationResponse saveEducation(EducationRequest request, String uuid) {
        SchoolId id = new SchoolId(request.getEducationList().get(0).getId()
                , request.getEducationList().get(0).getResumeId());

        String resumeId = checkResumeId(id);

        for (EducationRequest.EducationRequests row :
                request.getEducationList()) {
            row.setResumeId(resumeId);
            if (row.getId() == null) row.setId(getRandomId());
        }

        List<School> response = mapper.mapAsList(request.getEducationList(), School.class);

        for (School record :
                response) {
            repository.save(record);
        }

        return getEducationbyResourceId(resumeId);
    }

    @Override
    public Map<String, String> deleteEducationbyResumeId(String resumeId) {
        List<School> response = repository.findByIdResumeId(resumeId);

        if(response.isEmpty())
            throw new CustomNotFoundException(String.format("No Education records were found for resumeId %s", resumeId));

        for (School record :
                response)
            deleteEducationbyId(record.getId().getResumeId(), record.getId().getId());

        Map<String, String> map = new HashMap<>();
        map.put("resumeId", resumeId);

        return map;
    }

    @Override
    public Map<String, String> deleteEducationbyId(String resumeId, String id) {
        SchoolId wid = new SchoolId(id, resumeId);

        checkResumeId(wid);

        School response = repository.findById(wid)
                .orElseThrow(() -> new CustomNotFoundException(String.format("Education with id% was not found", wid.getId())));

        repository.delete(response);
        Map<String, String> map = new HashMap<>();
        map.put("id", wid.getId());
        map.put("resumeId", wid.getResumeId());

        return map;
    }

    private String checkResumeId(SchoolId wid) {
        return resumeRepository.findById(wid.getResumeId())
                .orElseThrow(() -> new CustomNotFoundException(String.format("Resume with id %s was not found", wid.getResumeId())))
                .getId();
    }
}
