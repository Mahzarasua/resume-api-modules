package com.mahzarasua.resumeapi.education.service;

import com.mahzarasua.resumeapi.education.domain.EducationRequest;
import com.mahzarasua.resumeapi.education.domain.EducationResponse;

import java.util.Map;

public interface EducationService {
    EducationResponse getEducationbyResourceId(String resumeId);
    EducationResponse saveEducation(EducationRequest request, String resumeId);
    Map<String, String> deleteEducationbyResumeId(String resumeId);
    Map<String, String> deleteEducationbyId(String resumeId, String id);
}
