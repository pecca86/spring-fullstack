package com.example.fullstack.student;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(name = "student_email_unique", columnNames = "email"))
public class Student {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    private Long id;
    @NotBlank(message = "First name cannot be blank!")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotBlank(message = "Last name cannot be blank!")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Email(message = "Invalid email format!")
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @NotNull(message = "Must choose a gender!")
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Student(String firstName, String lastName, String email, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }
}
