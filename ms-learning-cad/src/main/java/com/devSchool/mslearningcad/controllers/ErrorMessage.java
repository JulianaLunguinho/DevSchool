package com.devSchool.mslearningcad.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorMessage {

    private int code;

    private String message;

    public static ResponseEntity createErrorMessage(int status, String message) {
        ErrorMessage errorMessage = new ErrorMessage(status, message);
        return ResponseEntity.status(status).body(errorMessage);
    }

}
