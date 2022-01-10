package com.devSchool.mslearningattendance.data;

import com.devSchool.mslearningattendance.domain.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AttendanceRepository extends CrudRepository<Attendance, UUID> {

    List<Attendance> findAllByStudentId(UUID studentId);

}
