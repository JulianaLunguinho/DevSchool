package com.devSchool.mslearningattendance.controllers;

import com.devSchool.mslearningattendance.services.AttendanceService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AttendanceControllerTest {

    @MockBean
    private AttendanceService attendanceService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldSaveStudentAttendance() throws Exception {
        var studentId = "ace397dd-107c-4e49-b446-64ab97391b55";
        var body = "{\"courseId\":\"113026ad-8fae-48f6-ba86-012634eb33dc\",\"attendanceStatus\":true}";
        var result = ResponseEntity.status(HttpStatus.CREATED).build();

        when(attendanceService.saveStudentAttendance(any(), any())).thenReturn(result);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());

        this.mockMvc.perform(post("/v1/{studentId}/attendance", studentId)
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void shouldNotSaveStudentAttendanceAndReturnErrorStatus400() throws Exception {
        var studentId = "ace397dd-107c-4e49-b446-64ab97391b55";
        var body = "{\"courseId\":\"\",\"attendanceStatus\":true}";
        var result = ResponseEntity.status(HttpStatus.CREATED).build();

        when(attendanceService.saveStudentAttendance(any(), any())).thenReturn(result);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());

        this.mockMvc.perform(post("/v1/{studentId}/attendance", studentId)
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is(400))
                .andReturn();
    }

    @Test
    void shouldGetAttendancesByStudent() throws Exception {
        var studentId = "ace397dd-107c-4e49-b446-64ab97391b55";
        var result = ResponseEntity.ok().build();

        when(attendanceService.getAttendancesByStudent(any())).thenReturn(result);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());

        this.mockMvc.perform(get("/v1/{studentId}/attendances", studentId)
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldNotGetAttendancesByStudentAndReturnErrorStatus404() throws Exception {
        var result = ResponseEntity.ok().build();

        when(attendanceService.getAttendancesByStudent(any())).thenReturn(result);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());

        this.mockMvc.perform(get("/v1/{studentId}/attendances", "")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();
    }

}