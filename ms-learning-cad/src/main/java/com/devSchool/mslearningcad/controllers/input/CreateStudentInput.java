package com.devSchool.mslearningcad.controllers.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateStudentInput {

    private String firstName;

    private String lastName;

    private String document;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    private UUID courseId;

}
