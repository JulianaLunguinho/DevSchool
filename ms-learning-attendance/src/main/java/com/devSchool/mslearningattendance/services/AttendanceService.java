package com.devSchool.mslearningattendance.services;

import com.devSchool.mslearningattendance.client.CourseClient;
import com.devSchool.mslearningattendance.client.result.CourseResult;
import com.devSchool.mslearningattendance.controllers.ErrorMessage;
import com.devSchool.mslearningattendance.controllers.input.RegisterAttendanceInput;
import com.devSchool.mslearningattendance.data.AttendanceRepository;
import com.devSchool.mslearningattendance.domain.Attendance;
import com.devSchool.mslearningattendance.domain.Student;
import com.devSchool.mslearningattendance.services.result.AttendancesByStudentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseClient courseClient;

    public ResponseEntity saveStudentAttendance(RegisterAttendanceInput registerAttendanceInput, UUID studentId) {

        try {
            studentService.findStudentById(studentId);
        } catch (Exception e) {
            return ErrorMessage.createErrorMessage(404, "Aluno não encontrado!");
        }

        CourseResult course;

        try {
            course = courseClient.searchCourse(registerAttendanceInput.getCourseId());

        } catch (Exception e) {
            return ErrorMessage.createErrorMessage(404, e.getMessage());
        }

        if(ObjectUtils.isEmpty(course)) {
            return ErrorMessage.createErrorMessage(404, "Curso não encontrado!");
        }

        Attendance attendance = new Attendance(UUID.randomUUID(), studentId, registerAttendanceInput.getCourseId(),
                LocalDate.now(), registerAttendanceInput.getAttendanceStatus());
        attendanceRepository.save(attendance);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity getAttendancesByStudent(UUID studentId) {
        Student student;
        try {
            student = studentService.findStudentById(studentId);
        } catch (Exception e) {
            return ErrorMessage.createErrorMessage(404, "Aluno não encontrado!");
        }

        List<Attendance> attendances = attendanceRepository.findAllByStudentId(studentId);

        AttendancesByStudentResult attendancesByStudentResult = new AttendancesByStudentResult(student.getFullName(),
                student.getCourseId(), attendances);

        return ResponseEntity.ok(attendancesByStudentResult);
    }

}
