package com.devSchool.mslearningattendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MsLearningAttendanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsLearningAttendanceApplication.class, args);
	}

}
