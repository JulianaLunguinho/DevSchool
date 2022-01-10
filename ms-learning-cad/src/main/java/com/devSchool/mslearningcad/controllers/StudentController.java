package com.devSchool.mslearningcad.controllers;

import com.devSchool.mslearningcad.controllers.input.CreateStudentInput;
import com.devSchool.mslearningcad.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity createStudent(@RequestBody CreateStudentInput createStudentInput) {

        if(ObjectUtils.isEmpty(createStudentInput.getBirthdate())
                || ObjectUtils.isEmpty(createStudentInput.getCourseId())
                || ObjectUtils.isEmpty(createStudentInput.getDocument())
                || ObjectUtils.isEmpty(createStudentInput.getFirstName())
                || ObjectUtils.isEmpty(createStudentInput.getLastName())) {

            return ErrorMessage.createErrorMessage(400, "Informe todos os campos!");

        }

        return studentService.createStudent(createStudentInput);
    }

    @GetMapping("/student")
    public ResponseEntity getStudentByStudentId(@RequestParam("studentId") UUID studentId) {

        if(ObjectUtils.isEmpty(studentId)) {
            return ErrorMessage.createErrorMessage(400, "Informe o studentId!");
        }

        return studentService.getStudentByStudentId(studentId);
    }

}
