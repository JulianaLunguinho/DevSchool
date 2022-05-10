package com.devSchool.mslearningattendance.controllers.input;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RegisterAttendanceInput {

    private UUID courseId;

    private Boolean attendanceStatus;

}
