package com.mahzarasua.resumeapi.education.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "EducationResponse")
public class EducationResponse {
    private List<EducationResponses> educationList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EducationResponses implements Serializable {
        private String id;
        private String resumeId;
        private String name;
        private String career;
        private String degree;
        private LocalDate startDate;
        private LocalDate endDate;
    }
}
