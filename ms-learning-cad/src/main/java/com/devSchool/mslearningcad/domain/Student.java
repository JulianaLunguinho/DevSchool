package com.devSchool.mslearningcad.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document("students")
public class Student {

    @Id
    private String id;

    @Indexed(unique = true)
    private UUID studentId;

    private String firstName;

    private String lastName;

    private String document;

    private LocalDate birthdate;

    private UUID courseId;

    private Boolean status;

    private LocalDateTime createdOn;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

}
