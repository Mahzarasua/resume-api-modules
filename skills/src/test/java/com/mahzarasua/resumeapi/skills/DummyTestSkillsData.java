package com.mahzarasua.resumeapi.skills;

import com.mahzarasua.resumeapi.configuration.util.DummyGenericTestData;
import com.mahzarasua.resumeapi.skills.domain.SkillsRequest;
import com.mahzarasua.resumeapi.skills.domain.SkillsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyTestSkillsData extends DummyGenericTestData {
    private static final Logger log = LoggerFactory.getLogger(DummyTestSkillsData.class);

    public static SkillsResponse dummySkillResponseData(){
        SkillsResponse tmp = new SkillsResponse();
        List<SkillsResponse.SkillsResponses> list = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(3)+1; i++) {
            list.add(dummySkillResponseList());
        }
        tmp.setSkills(list);
        return tmp;
    }

    public static SkillsResponse.SkillsResponses dummySkillResponseList(){
        SkillsResponse.SkillsResponses tmp = new SkillsResponse.SkillsResponses();
        tmp.setId(generateRandomIdString());
        tmp.setResumeId(generateRandomString());
        tmp.setName(generateRandomString());
        tmp.setPercentage(Integer.parseInt(generateDummyNumericString(2)));
        tmp.setType(generateRandomString());

        log.info("SkillsResponse generated: {}", tmp);

        return tmp;
    }

    public static SkillsRequest dummySkillRequestData(){
        SkillsRequest tmp = new SkillsRequest();
        List<SkillsRequest.SkillsRequests> skillsRequests = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(3)+1; i++) {
            skillsRequests.add(dummySkillRequestList());
        }
        tmp.setSkills(skillsRequests);
        return tmp;
    }

    public static SkillsRequest.SkillsRequests dummySkillRequestList(){
        SkillsRequest.SkillsRequests tmp = new SkillsRequest.SkillsRequests();
        tmp.setId(generateRandomIdString());
        tmp.setResumeId(generateRandomString());
        tmp.setName(generateRandomString());
        tmp.setPercentage(Integer.parseInt(generateDummyNumericString(2)));
        tmp.setType(generateRandomString());

        log.info("SkillRequest generated: {}", tmp);

        return tmp;
    }
}
