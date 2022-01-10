package com.devSchool.mslearningcourse.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document("courses")
public class Course {

    @Id
    private String id;

    @Indexed(unique = true)
    private UUID courseId;

    private String courseName;

    private Boolean status;

    private LocalDateTime createdOn;

}
