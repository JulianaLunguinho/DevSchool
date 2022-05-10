package com.devSchool.mslearningattendance.services;

import com.devSchool.mslearningattendance.Mocks;
import com.devSchool.mslearningattendance.client.CourseClient;
import com.devSchool.mslearningattendance.controllers.ErrorMessage;
import com.devSchool.mslearningattendance.data.AttendanceRepository;
import com.devSchool.mslearningattendance.domain.Attendance;
import com.devSchool.mslearningattendance.domain.Student;
import com.devSchool.mslearningattendance.services.result.AttendancesByStudentResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private StudentService studentService;

    @Mock
    private CourseClient courseClient;

    @InjectMocks
    AttendanceService attendanceService;

    Student student;

    @BeforeEach
    public void setup() {
        student = Mocks.getStudent();
        when(studentService.findStudentById(any())).thenReturn(student);
    }

    @Test
    void shouldSaveStudentAttendance() {
        var registerAttendanceInput = Mocks.getRegisterAttendanceInput();
        var courseResult = Mocks.getCourseResult();

        when(courseClient.searchCourse(any())).thenReturn(courseResult);

        assertEquals(attendanceService.saveStudentAttendance(registerAttendanceInput, UUID.randomUUID()),
                ResponseEntity.status(HttpStatus.CREATED).build());
    }

    @Test
    void shouldNotSaveStudentAttendanceBecauseStudentIsNull() {
        var registerAttendanceInput = Mocks.getRegisterAttendanceInput();

        when(studentService.findStudentById(any())).thenThrow(IllegalArgumentException.class);

        assertEquals(attendanceService.saveStudentAttendance(registerAttendanceInput, UUID.randomUUID()),
                ErrorMessage.createErrorMessage(404, "Aluno não encontrado!"));
    }

    @Test
    void shouldNotSaveStudentAttendanceBecauseCourseClientBroke() {
        var registerAttendanceInput = Mocks.getRegisterAttendanceInput();
        var courseResult = Mocks.getCourseResult();

        when(courseClient.searchCourse(any())).thenThrow(IllegalArgumentException.class);

        assertEquals(attendanceService.saveStudentAttendance(registerAttendanceInput, UUID.randomUUID()),
                ErrorMessage.createErrorMessage(404, null));
    }

    @Test
    void shouldNotSaveStudentAttendanceBecauseCourseIsNull() {
        var registerAttendanceInput = Mocks.getRegisterAttendanceInput();

        when(courseClient.searchCourse(any())).thenReturn(null);

        assertEquals(attendanceService.saveStudentAttendance(registerAttendanceInput, UUID.randomUUID()),
                ErrorMessage.createErrorMessage(404, "Curso não encontrado!"));
    }

    @Test
    void shouldGetAttendancesByStudent() {
        List<Attendance> attendances = new ArrayList<>();
        var attendancesByStudentResult = new AttendancesByStudentResult(student.getFullName(), student.getCourseId(), attendances);

        when(attendanceRepository.findAllByStudentId(any())).thenReturn(attendances);

        assertEquals(attendanceService.getAttendancesByStudent(UUID.randomUUID()),
                ResponseEntity.ok(attendancesByStudentResult));
    }

    @Test
    void shouldNotGetAttendancesByStudentBecauseStudentIsNull() {
        when(studentService.findStudentById(any())).thenThrow(IllegalArgumentException.class);

        assertEquals(attendanceService.getAttendancesByStudent(UUID.randomUUID()),
                ErrorMessage.createErrorMessage(404, "Aluno não encontrado!"));
    }

}