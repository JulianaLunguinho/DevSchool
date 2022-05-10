package com.devSchool.mslearningattendance;

import com.devSchool.mslearningattendance.client.result.CourseResult;
import com.devSchool.mslearningattendance.controllers.input.RegisterAttendanceInput;
import com.devSchool.mslearningattendance.domain.Student;

import java.util.UUID;

public class Mocks {

    public static RegisterAttendanceInput getRegisterAttendanceInput() {
        return new RegisterAttendanceInput(UUID.randomUUID(), true);
    }

    public static Student getStudent() {
        return new Student(UUID.randomUUID(), "Joao Silva Teste", UUID.randomUUID());
    }

    public static CourseResult getCourseResult() {
        return new CourseResult(UUID.randomUUID(), "Curso de Teste", true);
    }

}
