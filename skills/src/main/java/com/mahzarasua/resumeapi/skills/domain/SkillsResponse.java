package com.mahzarasua.resumeapi.skills.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "SkillResponse")
public class SkillsResponse {

    private List<SkillsResponses> skills;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SkillsResponses {
        private String id;
        private String resumeId;
        private String name;
        private int percentage;
        private String type;
    }

}
