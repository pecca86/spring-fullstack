package com.example.fullstack.student;

import com.example.fullstack.exceptions.BadRequestException;
import com.example.fullstack.exceptions.StudentNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

// Alternative to initializing Autoclosable
// @ExtendedWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        // Initializes all mocks inside this class
        this.autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentService(studentRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void shouldGetAllStudents() {
        // when
        underTest.getStudents();
        // then, check that findAll method is invoked
        verify(studentRepository).findAll();
    }

    @Test
    void shouldAddStudent() {
        // Given
        Student s = new Student(
                "p",
                "r",
                "pr@p.com",
                Gender.FEMALE
        );

        // When
        underTest.addStudent(s);

        // Then, test that student repo's save method actually was invoked
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        // argumentCaptor captures the actual student that was passed to the save method
        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(s).isEqualTo(capturedStudent);
    }

    @Test
    void shouldThrowEmailIsTakenException() {
        // Given

        Student s2 = new Student(
                "p",
                "r",
                "pr@p.com",
                Gender.FEMALE
        );

        // Set what we want the mocked repo to return
        given(studentRepository.findStudentByEmail(s2.getEmail()))
                .willReturn(s2);


        // Then
        assertThatThrownBy(() -> underTest.addStudent(s2))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email already taken!");

        // Verify that addStudent method will never call save
        verify(studentRepository, never()).save(any());
    }

    @Test
    void shouldThrowStudentNotFoundExceptionWhenDeleting() {

        assertThatThrownBy(() -> underTest.deleteStudent(2020L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("No student found with given id");

        verify(studentRepository, never()).deleteById(any());

    }

    @Test
    void shouldCallDeleteStudentWithCorrectId() {
        // Given
        Student s = new Student(
                "p",
                "r",
                "pr@p.com",
                Gender.FEMALE
        );

        given(studentRepository.getStudentById(s.getId())).willReturn(s);

        // When
        underTest.deleteStudent(s.getId());

        // Then, test that student repo's save method actually was invoked
        ArgumentCaptor<Long> studentArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        // argumentCaptor captures the actual student that was passed to the delete method
        verify(studentRepository).deleteById(studentArgumentCaptor.capture());

        Long capturedId = studentArgumentCaptor.getValue();

        assertThat(s.getId()).isEqualTo(capturedId);
    }
}