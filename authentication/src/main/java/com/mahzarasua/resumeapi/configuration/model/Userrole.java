package com.mahzarasua.resumeapi.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userrole {
    @EmbeddedId
    private UserroleId id;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserroleId implements Serializable {
        @Column(name = "user_id")
        private String userId;
        @Column(name = "role_id")
        private String roleId;
    }
}
