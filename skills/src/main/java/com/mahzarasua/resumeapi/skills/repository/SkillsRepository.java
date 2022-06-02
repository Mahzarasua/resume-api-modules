package com.mahzarasua.resumeapi.skills.repository;

import com.mahzarasua.resumeapi.configuration.model.Skill;
import com.mahzarasua.resumeapi.configuration.model.Skill.SkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends JpaRepository<Skill, SkillId> {
    List<Skill> findByIdResumeId(String id);
}
