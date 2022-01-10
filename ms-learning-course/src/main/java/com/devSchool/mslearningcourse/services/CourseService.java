package com.devSchool.mslearningcourse.services;

import com.devSchool.mslearningcourse.data.CourseRepository;
import com.devSchool.mslearningcourse.domain.Course;
import com.devSchool.mslearningcourse.services.result.CreateCourseResult;
import com.devSchool.mslearningcourse.services.result.SearchAllCoursesResult;
import com.devSchool.mslearningcourse.services.result.SearchCourseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public ResponseEntity createCourse(String courseName) {

        UUID courseId = UUID.randomUUID();

        Course course = new Course();
        course.setCourseId(courseId);
        course.setCourseName(courseName);
        course.setStatus(true);
        course.setCreatedOn(LocalDateTime.now());

        courseRepository.save(course);

        CreateCourseResult createCourseResult = new CreateCourseResult(courseId);

        return ResponseEntity.ok(createCourseResult);

    }

    public ResponseEntity searchCourses() {
        List<Course> courses = courseRepository.findAll();

        List<SearchCourseResult> courseResultList = new ArrayList<>();

        for(Course course : courses) {
            SearchCourseResult searchCourseResult = new SearchCourseResult(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getStatus());

            courseResultList.add(searchCourseResult);
        }

        SearchAllCoursesResult allCoursesResult = new SearchAllCoursesResult(courseResultList);

        return ResponseEntity.ok(allCoursesResult);
    }

    public ResponseEntity searchCourseByCourseId(UUID courseId) {
        Course course = courseRepository.findByCourseId(courseId);

        SearchCourseResult searchCourseResult = new SearchCourseResult(
                course.getCourseId(), course.getCourseName(), course.getStatus());

        return ResponseEntity.ok(searchCourseResult);
    }

}
