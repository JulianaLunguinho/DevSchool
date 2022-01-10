package com.devSchool.mslearningcad.data;

import com.devSchool.mslearningcad.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.UUID;

public interface StudentRepository extends MongoRepository<Student, String> {

    @Query("{ 'studentId' : ?0 }")
    Student findByStudentId(UUID studentId);

}
