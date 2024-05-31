package com.ums.Universitymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer course_id;

    private String courseName; // Name of the course

    private Integer credits; // Number of credits associated with the course

    private String prerequisites; // Information about any prerequisites required for this course

    private String syllabus; // Details of the course syllabus

    private Integer enrollmentCapacity; // Maximum number of students allowed to enroll in this course

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments;

    @OneToMany(mappedBy = "course")
    private Set<Assignment> assignments;

    @OneToMany(mappedBy = "course")
    private Set<TimeTable> timeTables;

    @OneToMany(mappedBy = "course")
    private Set<Exam> exams;

    @ManyToMany(mappedBy = "courses")
    private Set<Faculty> faculties;

}
