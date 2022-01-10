package com.devSchool.mslearningcourse.services.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchCourseResult {

    private UUID courseId;

    private String courseName;

    private Boolean status;

}
