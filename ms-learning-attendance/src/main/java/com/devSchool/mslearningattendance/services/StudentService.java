package com.devSchool.mslearningattendance.services;

import com.devSchool.mslearningattendance.domain.Student;
import com.google.gson.Gson;
import com.devSchool.mslearningattendance.data.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){
        log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());

        Gson gson = new Gson();
        Student student = gson.fromJson(payload.value(), Student.class);

        studentRepository.save(student);
    }

    public Student findStudentById(UUID studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if(!student.isPresent()) {
            throw new IllegalArgumentException("StudentId inválido!");
        }
        return student.get();
    }

}
