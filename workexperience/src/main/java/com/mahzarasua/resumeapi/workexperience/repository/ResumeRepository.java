package com.mahzarasua.resumeapi.workexperience.repository;

import com.mahzarasua.resumeapi.configuration.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, String> {
}
