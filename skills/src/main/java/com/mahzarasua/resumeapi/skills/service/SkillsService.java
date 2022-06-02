package com.mahzarasua.resumeapi.skills.service;

import com.mahzarasua.resumeapi.skills.domain.SkillsRequest;
import com.mahzarasua.resumeapi.skills.domain.SkillsResponse;

import java.util.Map;

public interface SkillsService {
    SkillsResponse getSkillsbyResourceId(String resumeId);
    SkillsResponse saveSkills(SkillsRequest request, String resumeId);
    Map<String, String> deleteSkillsbyResumeId(String resumeId);
    Map<String, String> deleteSkillsbyId(String resumeId, String id);
}
