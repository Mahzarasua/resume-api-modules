package com.mahzarasua.resumeapi.configuration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    @EmbeddedId
    private SchoolId id;

    private String name;
    private String career;
    private String degree;
    private LocalDate startDate;
    private LocalDate endDate;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SchoolId implements Serializable{
        @Column(name = "id")
        private String id;
        @Column(name = "resume_id")
        private String resumeId;
    }

    @ManyToOne
    @JoinColumn(name = "resume_id", insertable = false, updatable = false)
    @Exclude
    @JsonIgnore
    private Resume resume;

    @PreRemove
    public void preRemoveSchool(){
        resume.getSchools().remove(this);
    }
}
