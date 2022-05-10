package com.devSchool.mslearningattendance.services;

import com.devSchool.mslearningattendance.Mocks;
import com.devSchool.mslearningattendance.data.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldFindStudentById() {
        var studentId = UUID.randomUUID();
        var student = Mocks.getStudent();

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        assertEquals(studentService.findStudentById(studentId), student);
    }

    @Test
    void shouldNotFindStudentByIdAndTrowsIllegalArgumentException() {
        UUID studentId = UUID.randomUUID();

        when(studentRepository.findById(studentId)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> studentService.findStudentById(studentId));
    }

}
