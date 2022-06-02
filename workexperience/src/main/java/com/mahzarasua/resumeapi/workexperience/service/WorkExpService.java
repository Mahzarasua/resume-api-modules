package com.mahzarasua.resumeapi.workexperience.service;

import com.mahzarasua.resumeapi.workexperience.domain.WorkExpRequest;
import com.mahzarasua.resumeapi.workexperience.domain.WorkExpResponse;

public interface WorkExpService {
    WorkExpResponse getWorkExpbyResourceId(String resumeId);
    WorkExpResponse saveWorkExp(WorkExpRequest request, String resumeId);
    String deleteWorkExpbyResumeId(String resumeId);
    String deleteWorkExpbyId(String resumeId, String id);
}
