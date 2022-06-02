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
@Schema(description = "GetResumeResponse")
public class GetResumeResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private String city;
    private String state;
    private String country;
    private String email;
    private String phone;
    private String summary;
    private LocalDate creationDate;
    private List<Skill> skills;
    private List<School> schools;
    private List<WorkExperience> workExperiences;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Skill {
        private String name;
        private int percentage;
        private String type;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class School {
        private String name;
        private String career;
        private String degree;
        private LocalDate startDate;
        private LocalDate endDate;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WorkExperience {
        private String title;
        private String company;
        private boolean currentJob;
        private String description;
        private LocalDate startDate;
        private LocalDate endDate;
    }
}
