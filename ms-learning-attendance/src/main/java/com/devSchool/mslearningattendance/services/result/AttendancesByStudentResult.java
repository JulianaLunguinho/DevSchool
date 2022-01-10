package com.devSchool.mslearningattendance.services.result;

import com.devSchool.mslearningattendance.domain.Attendance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AttendancesByStudentResult {

    private String fullName;

    private UUID courseId;

    private List<Attendance> attendances;

}
