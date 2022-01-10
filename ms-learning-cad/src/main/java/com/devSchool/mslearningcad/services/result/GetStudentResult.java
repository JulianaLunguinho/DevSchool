package com.devSchool.mslearningcad.services.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class GetStudentResult {

    private String fullName;

    private String document;

    private LocalDate birthdate;

    private String courseName;

    private Boolean status;

}
