package com.example.fullstack.student;

import com.example.fullstack.exceptions.BadRequestException;
import com.example.fullstack.exceptions.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.invoke.StringConcatException;
import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public void addStudent(Student student) {
        // check if email is taken
        if (studentRepository.findStudentByEmail(student.getEmail()) != null) {
            throw new BadRequestException("Email already taken!");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        // Check if student exists
        if (studentRepository.getStudentById(id) == null) {
            throw new StudentNotFoundException("No student found with given id");
        }
        studentRepository.deleteById(id);
    }


}
