package com.mahzarasua.resumeapi.skills.service;

import com.mahzarasua.resumeapi.configuration.exception.CustomNotFoundException;
import com.mahzarasua.resumeapi.configuration.model.Skill;
import com.mahzarasua.resumeapi.configuration.model.Skill.SkillId;
import com.mahzarasua.resumeapi.skills.domain.SkillsRequest;
import com.mahzarasua.resumeapi.skills.domain.SkillsResponse;
import com.mahzarasua.resumeapi.skills.mapper.SkillsMapper;
import com.mahzarasua.resumeapi.skills.repository.ResumeRepository;
import com.mahzarasua.resumeapi.skills.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mahzarasua.resumeapi.configuration.util.DataServiceUtil.getRandomId;

@Service
public class SkillsServiceImpl implements SkillsService {

    @Autowired
    private SkillsRepository repository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private SkillsMapper mapper;

    @Override
    public SkillsResponse getSkillsbyResourceId(String resumeId) {
        List<Skill> list = repository.findByIdResumeId(resumeId);

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format("Resume with id %s was not found", resumeId));

        List<SkillsResponse.SkillsResponses> skillsList = mapper.mapAsList(list, SkillsResponse.SkillsResponses.class);
        SkillsResponse response = new SkillsResponse();
        response.setSkills(skillsList);
        return response;
    }

    @Override
    public SkillsResponse saveSkills(SkillsRequest request, String uuid) {
        SkillId id = new SkillId(request.getSkills().get(0).getId()
                , request.getSkills().get(0).getResumeId());

        String resumeId = checkResumeId(id);

        for (SkillsRequest.SkillsRequests row :
                request.getSkills()) {
            row.setResumeId(resumeId);
            if (row.getId() == null) row.setId(getRandomId());
        }

        List<Skill> response = mapper.mapAsList(request.getSkills(), Skill.class);

        for (Skill record :
                response) {
            repository.save(record);
        }

        return getSkillsbyResourceId(resumeId);
    }

    @Override
    public Map<String, String> deleteSkillsbyResumeId(String resumeId) {
        List<Skill> response = repository.findByIdResumeId(resumeId);

        if(response.isEmpty())
            throw new CustomNotFoundException(String.format("No Skill records were found for resumeId %s", resumeId));

        for (Skill record :
                response)
            deleteSkillsbyId(record.getId().getResumeId(), record.getId().getId());

        Map<String, String> map = new HashMap<>();
        map.put("resumeId", resumeId);

        return map;
    }

    @Override
    public Map<String, String> deleteSkillsbyId(String resumeId, String id) {
        SkillId wid = new SkillId(id, resumeId);

        checkResumeId(wid);

        Skill response = repository.findById(wid)
                .orElseThrow(() -> new CustomNotFoundException(String.format("Skill with id %s was not found", wid.getId())));

        repository.delete(response);
        Map<String, String> map = new HashMap<>();
        map.put("id", wid.getId());
        map.put("resumeId", wid.getResumeId());

        return map;
    }

    private String checkResumeId(SkillId id) {
        return resumeRepository.findById(id.getResumeId())
                .orElseThrow(() -> new CustomNotFoundException(String.format("Resume with id %s was not found", id.getResumeId())))
                .getId();
    }
}
