package com.devSchool.mslearningcad.services.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateStudentEvent {

    private UUID studentId;

    private String fullName;

    private UUID courseId;

}
