package com.example.fullstack.integration;

import com.example.fullstack.student.Gender;
import com.example.fullstack.student.Student;
import com.example.fullstack.student.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test
 * Maven fail safe plugin will look for test classes named <name>IT
 */
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class StudentIT {
    
    // For sending http request
    @Autowired
    private MockMvc mockMvc;

    // JSON
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    private final Faker faker = new Faker();


    @Test
    void shouldPostNewStuddent() throws Exception {

        Student student = new Student(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                Gender.FEMALE
        );

        ResultActions perform = mockMvc
                .perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));

        // Assert we get a 200 response
        perform.andExpect(status().isOk());

        // Check that student is present in our database
        Student newStudent = studentRepository.findStudentByEmail(student.getEmail());
        assertThat(newStudent).isNotNull();
    }

    @Test
    void shouldDeleteStudent() throws Exception {
        Student student = new Student(
                "p",
                "r",
                "kakkakakakak@ka.com",
                Gender.FEMALE
        );

        mockMvc
                .perform(post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());

        Student added = studentRepository.findStudentByEmail(student.getEmail());

        ResultActions perform = mockMvc
                .perform(delete("/api/v1/students/"+added.getId()));
        perform.andExpect(status().isOk());

        Student deleted = studentRepository.findStudentByEmail(added.getEmail());
        assertThat(deleted).isNull();

    }
}
