package com.example.fullstack.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student getStudentById(Long id);

    Student findStudentByEmail(String email);

}
