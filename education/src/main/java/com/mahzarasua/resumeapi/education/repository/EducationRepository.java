package com.mahzarasua.resumeapi.education.repository;

import com.mahzarasua.resumeapi.configuration.model.School;
import com.mahzarasua.resumeapi.configuration.model.School.SchoolId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<School, SchoolId> {
    List<School> findByIdResumeId(String id);
}
