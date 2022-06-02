package com.mahzarasua.resumeapi.resume.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "ResumeRequest")
public class ResumeRequest {

    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String title;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String summary;

    @NotBlank
    @Email(regexp = "^[\\p{L}\\p{N}\\._%+-]+@[\\p{L}\\p{N}\\.\\-]+\\.[\\p{L}]{2,}$")
    private String email;

    @NotBlank
    private String phone;

    private List<Skill> skills;
    private List<WorkExperience> workExperiences;
    private List<School> schools;
    private LocalDate creationDate;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class School {
        private String id;
        private String resumeId;
        private String name;
        private String career;
        private LocalDate startDate;
        private LocalDate endDate;
        private String degree;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Skill {
        private String id;
        private String resumeId;
        @NotBlank
        private String name;
        @NotBlank
        private int percentage;
        @NotBlank
        private String type;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class WorkExperience {
        private String id;
        private String resumeId;
        private String title;
        private String company;
        private LocalDate startDate;
        private LocalDate endDate;
        private Boolean currentJob;
        private String description;
    }
}
