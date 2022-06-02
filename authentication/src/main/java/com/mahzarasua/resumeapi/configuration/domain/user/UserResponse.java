package com.mahzarasua.resumeapi.configuration.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private String id;
    private String username;
    private String password;
    private boolean active;
    private List<Role> roles;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Role {
        private String role;
    }
}
