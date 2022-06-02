package com.mahzarasua.resumeapi.education.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "EducationRequest")
public class EducationRequest {
    private List<EducationRequests> educationList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EducationRequests {
        private String id;
        private String resumeId;
        @NotBlank
        private String name;
        @NotBlank
        private String career;
        @NotBlank
        private String degree;
        @NotBlank
        private LocalDate startDate;
        private LocalDate endDate;
    }

}
