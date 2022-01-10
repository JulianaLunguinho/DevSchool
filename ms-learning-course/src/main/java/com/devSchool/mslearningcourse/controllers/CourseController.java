package com.devSchool.mslearningcourse.controllers;

import com.devSchool.mslearningcourse.controllers.input.CreateCourseInput;
import com.devSchool.mslearningcourse.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity createCourse(@RequestBody CreateCourseInput createCourseInput) {

        if(createCourseInput.getCourseName().isEmpty()) {
            return createErrorMessage(400, "Informe o courseName!");
        }

        return courseService.createCourse(createCourseInput.getCourseName());

    }

    @GetMapping(value = "/courses")
    public ResponseEntity searchCourses() {
        return courseService.searchCourses();
    }

    @GetMapping(value = "/course")
    public ResponseEntity searchCourseByCourseId(@RequestParam("courseId") UUID courseId) {

        if(ObjectUtils.isEmpty(courseId)) {
            return createErrorMessage(400, "Informe o courseId!");
        }

        return courseService.searchCourseByCourseId(courseId);
    }

    private ResponseEntity createErrorMessage(int status, String message) {
        ErrorMessage errorMessage = new ErrorMessage(status, message);
        return ResponseEntity.status(status).body(errorMessage);
    }

}
