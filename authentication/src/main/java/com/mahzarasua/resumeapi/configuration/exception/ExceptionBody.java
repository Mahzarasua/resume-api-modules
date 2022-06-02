package com.mahzarasua.resumeapi.configuration.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExceptionBody {
    private LocalDateTime timestamp;
    private Integer statusCode;
    private String error;
    private String message;
    private String path;
    private List<ErrorDetails> details;

    @Data
    public static class ErrorDetails {
        private String fieldName;
        private String errorMessage;
    }
}
