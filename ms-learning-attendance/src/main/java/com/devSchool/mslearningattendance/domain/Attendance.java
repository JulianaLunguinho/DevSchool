package com.devSchool.mslearningattendance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@RedisHash("Attendance")
public class Attendance implements Serializable {

    @Id
    private UUID attendanceId;

    @Indexed
    private UUID studentId;

    @Indexed
    private UUID courseId;

    private LocalDate classDate;

    private Boolean attendanceStatus;

}
