package com.example.fullstack.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    @GetMapping
    public List<Student> getStudents() {
        return Arrays.asList(
                new Student(1L, "Pekka", "Ranta-aho", "p@p.com", Gender.MALE),
                new Student(2L, "Ulla", "Knulla-aho", "uk@p.com", Gender.FEMALE),
                new Student(3L, "Jan-Lisa", "Pitt-Fitta", "jlpf@p.com", Gender.OTHER)
        );
    }
}
