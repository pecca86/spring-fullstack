package com.example.fullstack;

import com.example.fullstack.student.Gender;
import com.example.fullstack.student.Student;
import com.example.fullstack.student.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// In order for repository to connect to H2 DB
@DataJpaTest
class FullstackApplicationTests {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindStudentIfEmailExists() {
        // Given
        String email = "fisfjalle@farknockers.com";
        Student student = new Student(
                "Pekka",
                "Jätte-kuk",
                email,
                Gender.MALE
        );
        underTest.save(student);

        // When
        Boolean found = underTest.selectExistsEmail(email);

        // Then
        assertThat(found).isTrue();
    }

    @Test
    void shouldFindStudentByEmailIfExists() {
        Student student = new Student(
                "Kalle",
                "Stropp",
                "ks@sk.fi",
                Gender.OTHER
        );
        underTest.save(student);

        Student found = underTest.findStudentByEmail("ks@sk.fi");

        assertThat(student).isEqualTo(found);
    }

    @Test
    void shouldNotFindAnyStudentsWithGivenEmail() {
        Student s = underTest.findStudentByEmail("kakaka@ka");
        assertThat(s).isNull();
    }

    @Test
    void studentExistShouldBeFalse() {
        assertThat(underTest.selectExistsEmail("fgomgogmgom")).isFalse();
    }
}
