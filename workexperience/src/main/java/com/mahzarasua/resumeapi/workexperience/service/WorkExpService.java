package com.mahzarasua.resumeapi.workexperience.service;

import com.mahzarasua.resumeapi.workexperience.domain.WorkExpRequest;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpResponse;

import java.util.Map;

public interface WorkExpService {
    WorkExpResponse getWorkExpbyResourceId(String resumeId);
    WorkExpResponse saveWorkExp(WorkExpRequest request, String resumeId);
    Map<String, String> deleteWorkExpbyResumeId(String resumeId);
    Map<String, String> deleteWorkExpbyId(String resumeId, String id);
}
