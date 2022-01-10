package com.devSchool.mslearningcourse.data;

import com.devSchool.mslearningcourse.domain.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.UUID;

public interface CourseRepository extends MongoRepository<Course, String> {

    @Query("{ 'courseId' : ?0 }")
    Course findByCourseId(UUID courseId);

}
