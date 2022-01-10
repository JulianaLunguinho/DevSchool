package com.devSchool.mslearningattendance.controllers;

import com.devSchool.mslearningattendance.controllers.input.RegisterAttendanceInput;
import com.devSchool.mslearningattendance.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/v1")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping(value = "/{studentId}/attendance")
    public ResponseEntity saveStudentAttendance(@RequestBody RegisterAttendanceInput registerAttendanceInput,
                                                @PathVariable("studentId") UUID studentId) {
        if(ObjectUtils.isEmpty(studentId)
                || ObjectUtils.isEmpty(registerAttendanceInput.getCourseId())
                || ObjectUtils.isEmpty(registerAttendanceInput.getAttendanceStatus())) {
            return ErrorMessage.createErrorMessage(400, "Informe todos os campos!");
        }

        return attendanceService.saveStudentAttendance(registerAttendanceInput, studentId);

    }

    @GetMapping(value = "/{studentId}/attendances")
    public ResponseEntity getAttendancesByStudent(@PathVariable("studentId") UUID studentId) {
        if(ObjectUtils.isEmpty(studentId)) {
            return ErrorMessage.createErrorMessage(400, "Informe o studentId!");
        }

        return attendanceService.getAttendancesByStudent(studentId);
    }

}
