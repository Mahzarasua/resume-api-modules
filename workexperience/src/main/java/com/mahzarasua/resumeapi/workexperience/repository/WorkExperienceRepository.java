package com.mahzarasua.resumeapi.workexperience.repository;

import com.mahzarasua.resumeapi.configuration.model.WorkExperience;
import com.mahzarasua.resumeapi.configuration.model.WorkExperience.WorkExperienceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, WorkExperienceId> {
    List<WorkExperience> findByIdResumeId(String id);
}
