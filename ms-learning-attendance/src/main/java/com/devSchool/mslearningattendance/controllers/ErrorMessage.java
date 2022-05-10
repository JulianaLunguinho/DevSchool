package com.devSchool.mslearningattendance.controllers;

import lombok.*;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private int code;

    private String message;

    public static ResponseEntity<ErrorMessage> createErrorMessage(int status, String message) {
        ErrorMessage errorMessage = new ErrorMessage(status, message);
        return ResponseEntity.status(status).body(errorMessage);
    }

}
