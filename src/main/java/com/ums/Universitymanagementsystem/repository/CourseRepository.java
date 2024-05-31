package com.ums.Universitymanagementsystem.repository;

import com.ums.Universitymanagementsystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByCourseName(String courseName);

}
