package com.devSchool.mslearningattendance.client.result;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CourseResult {

    private UUID courseId;

    private String courseName;

    private Boolean status;

}
