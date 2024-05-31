package com.ums.Universitymanagementsystem.repository;

import com.ums.Universitymanagementsystem.entity.Course;
import com.ums.Universitymanagementsystem.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    List<Exam> findByCourse(Course course);
}
