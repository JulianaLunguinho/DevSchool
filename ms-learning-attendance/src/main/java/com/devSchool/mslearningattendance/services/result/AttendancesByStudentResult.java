package com.devSchool.mslearningattendance.services.result;

import com.devSchool.mslearningattendance.domain.Attendance;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AttendancesByStudentResult {

    private String fullName;

    private UUID courseId;

    private List<Attendance> attendances;

}
