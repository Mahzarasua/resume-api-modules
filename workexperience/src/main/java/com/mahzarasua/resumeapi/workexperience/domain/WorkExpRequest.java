package com.mahzarasua.resumeapi.workexperience.domain;

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
@Schema(description = "WorkExpRequest")
public class WorkExpRequest {
    private List<WorkExpRequests> workExperiences;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WorkExpRequests{
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
