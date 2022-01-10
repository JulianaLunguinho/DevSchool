package com.devSchool.mslearningcad.services;

import com.devSchool.mslearningcad.client.CourseClient;
import com.devSchool.mslearningcad.client.result.CourseResult;
import com.devSchool.mslearningcad.controllers.ErrorMessage;
import com.devSchool.mslearningcad.controllers.input.CreateStudentInput;
import com.devSchool.mslearningcad.data.StudentRepository;
import com.devSchool.mslearningcad.domain.Student;
import com.devSchool.mslearningcad.services.result.CreateStudentEvent;
import com.devSchool.mslearningcad.services.result.CreateStudentResult;
import com.devSchool.mslearningcad.services.result.GetStudentResult;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    private CourseClient courseClient;

    public ResponseEntity createStudent(CreateStudentInput createStudentInput) {

        CourseResult courseResult;
        try {
            courseResult = courseClient.searchCourse(createStudentInput.getCourseId());
            if(ObjectUtils.isEmpty(courseResult)) {
                throw new IllegalArgumentException("CourseId inválido!");
            }
        } catch (Exception e) {
            return ErrorMessage.createErrorMessage(500, e.getMessage());
        }

        Student student = new Student();
        UUID studentId = UUID.randomUUID();

        student.setFirstName(createStudentInput.getFirstName());
        student.setLastName(createStudentInput.getLastName());
        student.setDocument(createStudentInput.getDocument());
        student.setBirthdate(createStudentInput.getBirthdate());
        student.setCourseId(createStudentInput.getCourseId());

        student.setStatus(true);
        student.setCreatedOn(LocalDateTime.now());
        student.setStudentId(studentId);

        studentRepository.save(student);

        CreateStudentEvent createStudentEvent = new CreateStudentEvent(studentId, student.getFullName(), student.getCourseId());
        Gson gson = new Gson();
        kafkaProducerService.send(gson.toJson(createStudentEvent));

        CreateStudentResult createStudentResult = new CreateStudentResult(studentId);

        return ResponseEntity.ok(createStudentResult);

    }

    public ResponseEntity getStudentByStudentId(UUID studentId) {
        Student student = studentRepository.findByStudentId(studentId);

        if(ObjectUtils.isEmpty(student)) {
            return ErrorMessage.createErrorMessage(400, "Aluno não encontrado!");
        }

        CourseResult courseResult;

        try {
            courseResult = courseClient.searchCourse(student.getCourseId());
        } catch (Exception e) {
            return ErrorMessage.createErrorMessage(500, e.getMessage());
        }

        GetStudentResult getStudentResult = new GetStudentResult(student.getFullName(), student.getDocument(),
                student.getBirthdate(), courseResult.getCourseName(), student.getStatus());

        return ResponseEntity.ok(getStudentResult);
    }

}
