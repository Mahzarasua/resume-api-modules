package com.mahzarasua.resumeapi.resume.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "WorkExpResponse")
public class WorkExpResponse {
    private List<WorkExpResponses> workExperiences;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WorkExpResponses{
        private String id;
        private String resumeId;
        private String title;
        private String company;
        private boolean currentJob;
        private String description;
        private LocalDate startDate;
        private LocalDate endDate;
    }

}
