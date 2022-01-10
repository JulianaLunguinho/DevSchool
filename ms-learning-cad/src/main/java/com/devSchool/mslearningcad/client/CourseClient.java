package com.devSchool.mslearningcad.client;

import com.devSchool.mslearningcad.client.result.CourseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.UUID;

@Service
public class CourseClient {

    public CourseResult searchCourse(UUID courseId) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8081/api/v1/course";

        ResponseEntity response = restTemplate.getForEntity(url + "?courseId=" + courseId, CourseResult.class);

        CourseResult courseResult = (CourseResult) response.getBody();
        return courseResult;
    };

}
