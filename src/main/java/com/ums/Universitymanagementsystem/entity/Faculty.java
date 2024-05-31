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
@Table(name="faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer faculty_id; // Primary Key

    private String facultyName; // Name of the faculty member

    private String qualifications; // Qualifications or degrees held by the faculty member

    @OneToMany(mappedBy = "faculty")
    private Set<Assignment> assignments;

    @OneToMany(mappedBy = "faculty")
    private Set<TimeTable> timeTables;

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "faculty_course",
            joinColumns = @JoinColumn(name = "faculty_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;
}
