package com.devSchool.mslearningattendance.controllers.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterAttendanceInput {

    private UUID courseId;

    private Boolean attendanceStatus;

}
