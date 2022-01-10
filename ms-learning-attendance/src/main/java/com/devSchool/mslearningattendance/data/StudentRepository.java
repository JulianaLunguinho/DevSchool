package com.devSchool.mslearningattendance.data;

import com.devSchool.mslearningattendance.domain.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, UUID> {

}
