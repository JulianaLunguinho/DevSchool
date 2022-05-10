package com.devSchool.mslearningattendance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@RedisHash("Student")
public class Student implements Serializable {

    @Id
    private UUID studentId;

    private String fullName;

    private UUID courseId;

}
