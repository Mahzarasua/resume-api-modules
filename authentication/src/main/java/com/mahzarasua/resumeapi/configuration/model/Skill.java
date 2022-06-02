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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @EmbeddedId
    private SkillId id;

    private String name;
    private int percentage;
    private String type;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SkillId implements Serializable {
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
    public void preRemoveSkill(){
        resume.getSkills().remove(this);
    }

}
