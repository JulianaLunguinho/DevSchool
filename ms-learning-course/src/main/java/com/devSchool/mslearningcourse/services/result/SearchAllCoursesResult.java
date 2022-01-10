package com.devSchool.mslearningcourse.services.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchAllCoursesResult {

    List<SearchCourseResult> courseResultList;

}
