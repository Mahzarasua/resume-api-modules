package com.mahzarasua.resumeapi.configuration.util;

import com.mahzarasua.resumeapi.configuration.model.Resume;
import com.mahzarasua.resumeapi.configuration.model.School;
import com.mahzarasua.resumeapi.configuration.model.Skill;
import com.mahzarasua.resumeapi.configuration.model.Skill.SkillId;
import com.mahzarasua.resumeapi.configuration.model.WorkExperience;
import com.mahzarasua.resumeapi.configuration.model.WorkExperience.WorkExperienceId;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DummyGenericTestData {
    private static final Logger log = LoggerFactory.getLogger(DummyGenericTestData.class);

    private static final int count = 8;

    public static String generateDummyAlphabeticString(int count){
        return RandomStringUtils.randomAlphabetic(count).toUpperCase();
    }

    public static String generateDummyAlphanumericString(int count){
        return RandomStringUtils.randomAlphanumeric(count).toUpperCase();
    }

    public static String generateDummyNumericString(int count){
        return RandomStringUtils.randomNumeric(count);
    }

    public static String generateRandomIdString(){
        return generateDummyAlphanumericString(count);
    }

    public static String generateRandomString(){
        return generateDummyAlphabeticString(count);
    }

    public static School dummySchoolData(){
        School tmp = new School();
        School.SchoolId id = new School.SchoolId();
        id.setId(generateRandomIdString());
        id.setResumeId(generateRandomIdString());

        tmp.setId(id);
        tmp.setName(generateRandomString());
        tmp.setCareer(generateRandomString());
        tmp.setDegree(generateRandomString());
        tmp.setStartDate(LocalDate.now());
        tmp.setEndDate(LocalDate.now());
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(dummyResumeData());
        tmp.setResume(new Resume());

        log.info("Education generated: {}", tmp);

        return tmp;
    }

    public static Skill dummySkillData(){
        Skill tmp = new Skill();
        SkillId id = new SkillId();
        id.setId(generateRandomIdString());
        id.setResumeId(generateRandomIdString());

        tmp.setId(id);
        tmp.setName(generateRandomString());
        tmp.setPercentage(Integer.parseInt(generateDummyNumericString(2)));
        tmp.setType(generateRandomString());
        tmp.setResume(new Resume());

        log.info("Skill generated: {}", tmp);

        return tmp;
    }

    public static WorkExperience dummyWorkExpData(){
        WorkExperience tmp = new WorkExperience();
        WorkExperience.WorkExperienceId id = new WorkExperienceId();
        id.setId(generateRandomIdString());
        id.setResumeId(generateRandomIdString());

        tmp.setId(id);
        tmp.setTitle(generateRandomString());
        tmp.setCompany(generateRandomString());
        tmp.setCurrentJob(true);
        tmp.setDescription(generateRandomString());
        tmp.setStartDate(LocalDate.now());
        tmp.setEndDate(LocalDate.now());
        tmp.setResume(new Resume());

        log.info("WorkExp generated: {}", tmp);

        return tmp;
    }

    public static Resume dummyResumeData(){
        Resume tmp = new Resume();
        tmp.setId(generateRandomIdString());
        tmp.setFirstName(generateRandomString());
        tmp.setLastName(generateRandomString());
        tmp.setTitle(generateRandomString());
        tmp.setCity(generateRandomString());
        tmp.setState(generateRandomString());
        tmp.setCountry(generateRandomString());
        tmp.setEmail(generateRandomString());
        tmp.setPhone(generateRandomString());
        tmp.setSummary(generateRandomString());
        tmp.setCreationDate(LocalDate.now());
        tmp.setSchools(new ArrayList<>());
        tmp.setWorkExperiences(new ArrayList<>());
        tmp.setSkills(new ArrayList<>());

        return tmp;
    }
}
