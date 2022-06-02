package com.mahzarasua.resumeapi.resume.service;

import com.mahzarasua.resumeapi.resume.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.resume.domain.ResumeRequest;
import com.mahzarasua.resumeapi.resume.domain.ResumeResponse;

import java.util.List;

public interface ResumeService {
    List<GetResumeResponse> getAllResumes();
    GetResumeResponse getResumeById(String resourceId);
    ResumeResponse saveResume(ResumeRequest resumeRequest, String resourceId);
    String deleteResumebyId(String resumeId);
}
